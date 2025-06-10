import HeaderComponent from "./HeaderComponent"
import { retrieveAllStudentsForTeacherIdApi } from "./api/TeacherApiService";
import { useState } from "react"
import { retrieveAllSubjectsForStudentIdApi } from "./api/StudentApiService"
import { useEffect } from "react"
import { AuthContext, useAuth } from './security/AuthContext'
import { BrowserRouter, Routes, Route, useNavigate, useParams, Link } from 'react-router-dom'

export default function TeacherListStudentsComponent() {

    const [students, setStudents] = useState([]);

    const { subject_id,department } = useParams();

     

    const authContext = useAuth()

    const navigate = useNavigate()

    const username = authContext.username

    useEffect(() => {
        refreshStudents();
    }, [username, subject_id,department]);
         
         function refreshStudents() {
            retrieveAllStudentsForTeacherIdApi(username,subject_id,department)
                 .then(response => {
                     console.log(response)
                     setStudents(response.data)
                 }
             )
             .catch(error => console.log(error))
         }

    return (
        <div className="container">
            <HeaderComponent></HeaderComponent>

            <table className='table'>
                <thead>
                    <tr>
                        <td><h3>Students</h3></td>
                    </tr>
                </thead>
                <tbody>
                    {
                        students.map(
                            student=>
                                <tr>
                                    <td>{student}</td>
                                </tr>
                        )
                    }
                    
                </tbody>
            </table>
        </div>
    )
}