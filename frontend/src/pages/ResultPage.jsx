import React, { useState } from 'react'
import api from '../api'
import jsPDF from 'jspdf'

export default function ResultPage({ income, deductions, onBack, result, setResult }){
  const [loading, setLoading] = useState(false)
  const [message, setMessage] = useState('')

  const compute = async ()=>{
    setLoading(true)
    try{
      const payload = {
        salary: Number(income.salary||0),
        businessIncome: Number(income.businessIncome||0),
        otherIncome: Number(income.otherIncome||0),
        deductions: {
          '80C': Number(deductions['80C']||0),
          '80D': Number(deductions['80D']||0),
          'others': Number(deductions.others||0)
        }
      }
      const res = await api.post('/calculateTax', payload)
      setResult(res.data)
    }catch(e){
      console.error(e)
      setMessage('Error computing tax')
    }finally{ setLoading(false) }
  }

  const exportClientPdf = ()=>{
    if(!result) return
    const doc = new jsPDF()
    doc.setFontSize(16)
    doc.text('Tax Summary', 20, 20)
    doc.setFontSize(12)
    doc.text(`Total income: ${result.totalIncome}`, 20, 40)
    doc.text(`Deductions: ${result.deductionsTotal}`, 20, 50)
    doc.text(`Taxable income: ${result.taxableIncome}`, 20, 60)
    doc.text(`Tax payable: ${result.taxPayable}`, 20, 70)
    doc.save('tax-summary.pdf')
  }

  const exportServerPdf = async ()=>{
    if(!result) return
    setLoading(true)
    try{
      const res = await api.post('/generatePDF', result, { responseType: 'arraybuffer' })
      const blob = new Blob([res.data], { type: 'application/pdf' })
      const url = window.URL.createObjectURL(blob)
      const a = document.createElement('a')
      a.href = url
      a.download = 'tax-summary-server.pdf'
      a.click()
      window.URL.revokeObjectURL(url)
    }catch(e){ console.error(e); setMessage('Error generating server PDF') }
    finally{ setLoading(false) }
  }

  return (
    <div>
      <h2>Result</h2>
      {!result ? (
        <div>
          <p>No result calculated yet.</p>
          <button onClick={compute} disabled={loading}>{loading? 'Computing...':'Compute Tax'}</button>
        </div>
      ) : (
        <div>
          <p>Total income: {result.totalIncome}</p>
          <p>Deductions: {result.deductionsTotal}</p>
          <p>Taxable income: {result.taxableIncome}</p>
          <p>Tax payable: {result.taxPayable}</p>
          <p>Applied slabs: {result.appliedSlabs}</p>

          <div style={{marginTop:10}}>
            <button onClick={onBack}>Back</button>
            <button onClick={exportClientPdf}>Export (client PDF)</button>
            <button onClick={exportServerPdf}>Export (server PDF)</button>
          </div>
        </div>
      )}
      {message && <div style={{color:'red'}}>{message}</div>}
    </div>
  )
}
