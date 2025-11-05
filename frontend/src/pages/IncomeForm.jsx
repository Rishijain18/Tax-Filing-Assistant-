import React from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik'
import * as Yup from 'yup'

const schema = Yup.object().shape({
  salary: Yup.number().min(0).required('Required'),
  businessIncome: Yup.number().min(0),
  otherIncome: Yup.number().min(0)
})

export default function IncomeForm({ onNext, defaultValues={} }){
  return (
    <Formik
      initialValues={{ salary: defaultValues.salary||0, businessIncome: defaultValues.businessIncome||0, otherIncome: defaultValues.otherIncome||0 }}
      validationSchema={schema}
      onSubmit={(values)=>{ onNext(values) }}
    >
      {() => (
        <Form>
          <div>
            <label>Salary:</label>
            <Field name="salary" type="number" />
            <ErrorMessage name="salary" component="div" style={{color:'red'}} />
          </div>

          <div>
            <label>Business Income:</label>
            <Field name="businessIncome" type="number" />
          </div>

          <div>
            <label>Other Income:</label>
            <Field name="otherIncome" type="number" />
          </div>

          <button type="submit">Next</button>
        </Form>
      )}
    </Formik>
  )
}
