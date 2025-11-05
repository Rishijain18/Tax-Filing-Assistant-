import React, { useState } from 'react'
import IncomeForm from './pages/IncomeForm'
import DeductionsForm from './pages/DeductionsForm'
import ResultPage from './pages/ResultPage'

export default function App(){
  const [step, setStep] = useState(1)
  const [incomeData, setIncomeData] = useState({})
  const [deductions, setDeductions] = useState({})
  const [result, setResult] = useState(null)

  return (
    <div style={{maxWidth:800, margin:'24px auto', padding:20, fontFamily:'Arial'}}>
      <h1>Tax Filing Assistant</h1>
      <div style={{marginBottom:20}}>
        <button onClick={()=>setStep(1)} disabled={step===1}>Income</button>
        <button onClick={()=>setStep(2)} disabled={step===2}>Deductions</button>
        <button onClick={()=>setStep(3)} disabled={step===3}>Result</button>
      </div>
      {step===1 && (
        <IncomeForm onNext={(data)=>{setIncomeData(data); setStep(2)}} defaultValues={incomeData} />
      )}
      {step===2 && (
        <DeductionsForm onBack={()=>setStep(1)} onNext={(d)=>{setDeductions(d); setStep(3)}} defaultValues={deductions} />
      )}
      {step===3 && (
        <ResultPage income={incomeData} deductions={deductions} onBack={()=>setStep(2)} result={result} setResult={setResult} />
      )}
    </div>
  )
}
