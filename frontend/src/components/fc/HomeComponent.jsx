import { BrowserRouter, Routes, Route, useNavigate, useParams, Link, Navigate } from 'react-router-dom'


export default function HomeComponent() {

    const navigate = useNavigate()
 
    function gotoStudentLogin() {

        navigate('/student-login')
    }
 
    function gotoTeacherLogin() {

        navigate('/teacher-login')
    }
 
    function gotoAdminLogin() {

        navigate('/admin-login')
    }

    return (
        <div className="homePage">
            Home Component
            <div className='login-buttons'>
                <div>
                    <button type="button" name="student-login" onClick={gotoStudentLogin}>Student</button>
                </div>
                <div>
                    <button type="button" name="teacher-login" onClick={gotoTeacherLogin}>Teacher</button>
                </div>
                <div>
                    <button type="button" name="admin-login" onClick={gotoAdminLogin}>Administrator</button>
                </div>
            </div>
        </div>

    )
}