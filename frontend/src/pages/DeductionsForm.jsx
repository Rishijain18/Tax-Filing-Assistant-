import React from 'react'
import { Formik, Form, Field } from 'formik'

export default function DeductionsForm({ onBack, onNext, defaultValues={} }){
  return (
    <Formik
      initialValues={{
        "80C": defaultValues['80C']||0,
        "80D": defaultValues['80D']||0,
        others: defaultValues.others||0
      }}
      onSubmit={(values)=> onNext(values)}
    >
      {() => (
        <Form>
          <div>
            <label>Section 80C (₹):</label>
            <Field name="80C" type="number" />
          </div>
          <div>
            <label>Section 80D (Health Insurance ₹):</label>
            <Field name="80D" type="number" />
          </div>
          <div>
            <label>Other Deductions (₹):</label>
            <Field name="others" type="number" />
          </div>

          <div style={{marginTop:10}}>
            <button type="button" onClick={onBack}>Back</button>
            <button type="submit">Compute</button>
          </div>
        </Form>
      )}
    </Formik>
  )
}
