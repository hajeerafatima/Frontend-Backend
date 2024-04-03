import React, { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import axios from 'axios'


const EditEmployee = () => {
    const { id } = useParams()
    const [employee, setEmployee] = useState({
        name: '',
        email: '',
        salary: '',
        address: '',
        skills_id: ''
      })

      const [skills, setSkills] = useState([])
      const navigate = useNavigate()

      useEffect(() => {

          const authToken = localStorage.getItem('authToken');

    axios.get('http://localhost:8080/dashboard/get/skills', {
      headers: {
        Authorization: `Bearer ${authToken}`
      }
    })
      .then(result => {
        if (result.data) {
          setSkills(result.data);
        }
      })          
       // axios.get('http://localhost:8080/dashboard/get/skills')
        //.then(result => {
         // if(result.data){
          //  setSkills(result.data)
         // }
        //}).catch(err => console.log(err))
      

      const fetchEmployeeData = async () => {
        try {
          const authToken = localStorage.getItem('authToken'); // Retrieve token from local storage
          if (id !== undefined) {
            const response = await axios.get(`http://localhost:8080/dashboard/manageemployees/${id}`, {
              headers: {
                Authorization: `Bearer ${authToken}` // Include token in request headers
              }
            });
            const employeeData = response.data;
            setEmployee({
              ...employee,
              name: employeeData.name,
              email: employeeData.email,
              salary: employeeData.salary,
              address: employeeData.address,
              skills_id: employeeData.skills_id
            });
          }
        } catch (error) {
          console.error(error);
        }
      };
  
      fetchEmployeeData();
    }, [id]);
  


        // Check if id is not undefined before making API call
//     if (id !== undefined) {
//       axios.get(`http://localhost:8080/dashboard/manageemployees/${id}`)
//           .then(response => {
//              const employeeData = response.data;
//              setEmployee({
//               ...employee,
//               name: employeeData.name,
//               email: employeeData.email,
//               salary: employeeData.salary,
//               address: employeeData.address,
//               skills_id: employeeData.skills_id,
//              })
//           }).catch(err => console.log(err))
//   }
// }, [id]


  const handleSubmit = (e) => {
    e.preventDefault()
    axios.put('http://localhost:8080/dashboard/edit_employee/'+id, employee)
    .then(result => {
      if(result.data) {
        navigate("/dashboard/manageemployees")
      } else {
        alert(result.data)
      }
    }).catch(err => console.log(err))
  }

  return (
    <div className="d-flex justify-content-center align-items-center mt-3">
    <div className="p-3 rounded w-50 border">
      <h3 className="text-center">Edit Employee</h3>
      <form className="row g-1" onSubmit={handleSubmit}>
        <div className="col-12">
          <label for="inputName" className="form-label">
            Name
          </label>
          <input
            type="text"
            className="form-control rounded-0"
            id="inputName"
            placeholder="Enter Name"
            value={employee.name}
            onChange={(e) =>
              setEmployee({ ...employee, name: e.target.value })
            }
          />
        </div>
        <div className="col-12">
          <label for="inputEmail4" className="form-label">
            Email
          </label>
          <input
            type="email"
            className="form-control rounded-0"
            id="inputEmail4"
            placeholder="Enter Email"
            value={employee.email}
            autoComplete="off"
            onChange={(e) =>
              setEmployee({ ...employee, email: e.target.value })
            }
          />
        </div>

        <div className="col-12">
          <label for="inputSalary" className="form-label">
            Salary
          </label>
          <input
            type="text"
            className="form-control rounded-0"
            id="inputSalary"
            placeholder="Enter Salary"
            value={employee.salary}
            autoComplete="off"
            onChange={(e) =>
              setEmployee({ ...employee, salary: e.target.value })
            }
          />
        </div>


        <div className="col-12">
          <label for="inputAddress" className="form-label">
            Address
          </label>
          <input
            type="text"
            className="form-control rounded-0"
            id="inputAddress"
            placeholder="1234 Main St"
            value={employee.address}
            autoComplete="off"
            onChange={(e) =>
              setEmployee({ ...employee, address: e.target.value })
            }
          />
        </div>
        <div className="col-12">
          <label for="category" className="form-label" >
            Skills
          </label>
          <select name="skills" id="skills" className="form-select"
            onChange={(e) => setEmployee({ ...employee, skills_id: e.target.value })}>
            {skills.map((s) => {
              return <option value={s.id}>{s.skills}</option>;
            })}
          </select>
        </div>

        <div className="col-12">
          <button type="submit" className="btn btn-primary w-100">
           Edit Employee
          </button>
        </div>
      </form>
    </div>
  </div>
  )
          }

export default EditEmployee



















/*import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import axios from 'axios'


const EditEmployee = () => {
    const { id } = useParams()
    const [employee, setEmployee] = useState({
        name: '',
        email: '',
        salary: '',
        designation: '',
        address: '',
        skills_id: ''
      })

      const [skills, setSkills] = useState([])

      useEffect(() => {
        axios.get('http://localhost:8080/dashboard/get/skills')
        .then(result => {
          if(result.data){
            setSkills(result.data)
          }
        }).catch(err => console.log(err))

        axios.get(`http://localhost:8080/dashboard/manageemployees/${parseInt(id)}`)
        // axios.get('http://localhost:8080/dashboard/manageemployees/'+id)
        .then(result =>{
            console.log(result.data)
        }).catch(err => console.log(err))
    }, [])

  return (
    <div className="d-flex justify-content-center align-items-center mt-3">
    <div className="p-3 rounded w-50 border">
      <h3 className="text-center">Edit Employee</h3>
      <form className="row g-1" >
        <div className="col-12">
          <label for="inputName" className="form-label">
            Name
          </label>
          <input
            type="text"
            className="form-control rounded-0"
            id="inputName"
            placeholder="Enter Name"
            onChange={(e) =>
              setEmployee({ ...employee, name: e.target.value })
            }
          />
        </div>
        <div className="col-12">
          <label for="inputEmail4" className="form-label">
            Email
          </label>
          <input
            type="email"
            className="form-control rounded-0"
            id="inputEmail4"
            placeholder="Enter Email"
            autoComplete="off"
            onChange={(e) =>
              setEmployee({ ...employee, email: e.target.value })
            }
          />
        </div>


        <label htmlFor="inputDesignation" className="form-label">
          Designation
        </label>
        <input
          type="text"
          className="form-control rounded-0"
          id="inputDesignation"
          placeholder="Designation"
          autoComplete="off"
          onChange={(e) =>
            setEmployee({ ...employee, designation: e.target.value })
          }
        />

        <div className="col-12">
          <label for="inputSalary" className="form-label">
            Salary
          </label>
          <input
            type="text"
            className="form-control rounded-0"
            id="inputSalary"
            placeholder="Enter Salary"
            autoComplete="off"
            onChange={(e) =>
              setEmployee({ ...employee, salary: e.target.value })
            }
          />
        </div>


        <div className="col-12">
          <label for="inputAddress" className="form-label">
            Address
          </label>
          <input
            type="text"
            className="form-control rounded-0"
            id="inputAddress"
            placeholder="1234 Main St"
            autoComplete="off"
            onChange={(e) =>
              setEmployee({ ...employee, address: e.target.value })
            }
          />
        </div>
        <div className="col-12">
          <label for="category" className="form-label" >
            Skills
          </label>
          <select name="skills" id="skills" className="form-select"
            onChange={(e) => setEmployee({ ...employee, skills_id: e.target.value })}>
            {skills.map((s) => {
              return <option value={s.id}>{s.skills}</option>;
            })}
          </select>
        </div>

        <div className="col-12">
          <button type="submit" className="btn btn-primary w-100">
            Add Employee
          </button>
        </div>
      </form>
    </div>
  </div>
  )
}

export default EditEmployee*/
