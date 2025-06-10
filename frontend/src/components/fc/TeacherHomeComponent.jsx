import HeaderComponent from "./HeaderComponent"
import { useState } from "react"
import { retrieveAllSubjectsForStudentIdApi } from "./api/StudentApiService"
import { useEffect } from "react"
import { AuthContext, useAuth } from './security/AuthContext'
import { BrowserRouter, Routes, Route, useNavigate, useParams, Link } from 'react-router-dom'
import {retrieveAllSubjectsForTeacherIdApi,retrieveAllChats} from "./api/TeacherApiService"
import { retrieveAllMaterialsForTeacherIdApi, retrieveAllAssignmentsForTeacherIdApi, retrieveAllStudentsForTeacherIdApi } from "./api/TeacherApiService"

export default function TeacherHomeComponent() {

    // const subjects = [
    //     {id:1, name:"Database Management Systems", department:"CSE" , semister:1},
    //     {id:2, name:"Computer Networks", department:"AI-ML" , semister:3},
    //     {id:3, name:"Operating Systems", department:"IT" , semister:7}     
    // ]

    const [subjects, setSubjects] = useState([])

    const [message, setMessage] = useState(null)

    const authContext = useAuth()

    const navigate = useNavigate()

    const username = authContext.username


    useEffect ( () => refreshSubjects(), [])
    
    function refreshSubjects() {
        retrieveAllSubjectsForTeacherIdApi(username)
            .then(response => {
                console.log(response)
                setSubjects(response.data)
            }
        )
        .catch(error => console.log(error))
    }

    function gotoMaterialsPage(subject_id,department) {

                // Fetch materials for the given subject_id and department
                retrieveAllMaterialsForTeacherIdApi(subject_id, department)
                .then(response => {
                    console.log(response)
                    // Assuming you want to navigate to a new route with the materials data
                    navigate(`/teacher/${department}/${subject_id}/materials`, { state: { materials: response.data } })
                })
                .catch(error => console.log(error))
    
    }

    function gotoAssignmentsPage(subject_id,department) {

        // Fetch materials for the given subject_id and department
        retrieveAllAssignmentsForTeacherIdApi(subject_id, department)
        .then(response => {
            console.log(response)
            // Assuming you want to navigate to a new route with the materials data
            navigate(`/teacher/${department}/${subject_id}/assignments`, { state: { assignments: response.data } })
        })
        .catch(error => console.log(error))

    }

    function gotoListStudentsPage(username,subject_id,department) {
        retrieveAllStudentsForTeacherIdApi(username)
        .then(response=> {
            console.log(response)
            navigate(`/teacher/${username}/${subject_id}/${department}/students`, { state: { students: response.data } })
        })
    }
    function gotoChatRoom(username,subject_id,department){
        retrieveAllChats(subject_id,department)
        .then(response=>{
            console.log(response)
            navigate(`/${subject_id}/${department}/chat`,{state:{students:response.data}})
        })
    }
    const sub_array=subjects.flatMap(subject=>subject.teacher_name)
    

    return(
        <div className="container">
            <HeaderComponent></HeaderComponent>
            <h3>Name: {sub_array[0]} </h3>
            <div></div>
            <table className='table'>
                <thead>
                    <tr>
                        <td>Subject Id</td>
                        <td>Subject Name</td>
                        <td>Department</td>
                        <td>Semester</td>
                    </tr>
                </thead>
                <tbody>
                    {
                        subjects.map(
                            subject=>
                                <tr>
                                    <td>{subject.subject_id}</td>
                                    <td>{subject.subject_name}</td>
                                    <td>{subject.department}</td>
                                    <td>{subject.semester}</td>
                                    <td><button className="btn btn-primary" onClick={()=>gotoMaterialsPage(subject.subject_id,subject.department)}>Materials</button></td>
                                    <td><button className="btn btn-primary" onClick={()=>gotoAssignmentsPage(subject.subject_id,subject.department)}>Assignments</button></td>
                                    <td><button className="btn btn-primary" onClick={()=>gotoListStudentsPage(username,subject.subject_id,subject.department)}>Students</button></td>
                                    <td><button className="btn btn-success" onClick={() => gotoChatRoom(username,subject.subject_id,subject.department)}>ChatRoom</button></td>
                                </tr>
                        )
                    }
                    
                </tbody>
            </table>
        </div>
    )
}