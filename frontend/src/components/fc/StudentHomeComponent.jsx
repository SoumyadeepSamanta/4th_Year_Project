import HeaderComponent from "./HeaderComponent"
import { useState } from "react"
import { retrieveAllSubjectsForStudentIdApi } from "./api/StudentApiService"
import { retrieveAllMaterialsForStudentIdApi } from "./api/StudentApiService"
import { useEffect } from "react"
import { AuthContext, useAuth } from './security/AuthContext'
import { BrowserRouter, Routes, Route, useNavigate, useParams, Link } from 'react-router-dom'

export default function StudentHomeComponent() {

    // const subjects = [
    //     {id:1, name:"Database Management Systems", faculty: "JS"},
    //     {id:2, name:"Computer Networks", faculty: "ABj"},
    //     {id:3, name:"Operating Systems", faculty: "DC"}     
    // ]

    const [subjects, setSubjects] = useState([])

    const [message, setMessage] = useState(null)

    const authContext = useAuth()

    const navigate = useNavigate()

    const username = authContext.username


    useEffect ( () => refreshSubjects(), [])
    
    function refreshSubjects() {
        retrieveAllSubjectsForStudentIdApi(username)
            .then(response => {
                console.log(response)
                setSubjects(response.data)
            }
        )
        .catch(error => console.log(error))
    }

    function gotoMaterialsPage(subject_id,department) {

        // Fetch materials for the given subject_id and department
        retrieveAllMaterialsForStudentIdApi(subject_id, department)
        .then(response => {
            console.log(response)
            // Assuming you want to navigate to a new route with the materials data
            navigate(`/student/${department}/${subject_id}/materials`, { state: { materials: response.data } })
        })
        .catch(error => console.log(error))

}

    return(
        <div className="container">
            <HeaderComponent></HeaderComponent>
            <h3>Name: </h3>
            <h3>Department: </h3>
            <h3>Semister: </h3>
            <div></div>
            <table className='table'>
                <thead>
                    <tr>
                        <td>Subject Id</td>
                        <td>Subject Name</td>
                        <td>Faculty Name</td>
                    </tr>
                </thead>
                <tbody>
                    {
                        subjects.map(
                            subject=>
                                <tr>
                                    <td>{subject.subject_id}</td>
                                    <td>{subject.subject_name}</td>
                                    <td>{subject.teacher_name}</td>
                                    <td><button type="button" className="btn btn-success" onClick={()=>gotoMaterialsPage(subject.subject_id,subject.department)}>Enter</button></td>
                                </tr>
                        )
                    }
                    
                </tbody>
            </table>
        </div>
    )
}