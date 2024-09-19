import { useState } from 'react'
import { BrowserRouter, Routes, Route, useNavigate, useParams, Link } from 'react-router-dom'
import { AuthContext, useAuth } from './security/AuthContext'

export default function LoginComponent() {

    const [username, setUsername] = useState('Buju')

    const [password, setPassword] = useState('')

    const [showSuccessMessage, setShowSuccessMessage] = useState(false)

    const [showErrorMessage, setShowErrorMessage] = useState(false)

    const navigate = useNavigate()

    const authContext = useAuth()

    function handleUsernameChange(event) {

        setUsername(event.target.value)
    }

    function handlePasswordChange(event) {

        setPassword(event.target.value)
    }

    async function handleSubmit() {

            if(await authContext.login(username, password)) {
                setShowSuccessMessage(true)
                setShowErrorMessage(false)
                navigate(`/welcome/${username}`)
            }
            else {
                setShowSuccessMessage(false)
                setShowErrorMessage(true)
            }
    }

    function SuccessMessageComponent() {

        if(showSuccessMessage){
            return <div className='successMessage' >Authenticated Successfully</div>
        }
        return null
    }

    function ErrorMessageComponent() {

        if(showErrorMessage){
            return <div className='errorMessage'>Authentication Failed. Please try again</div>
        }
        return null
    }

    return(
        <div className="Login">
            Login Component
            <SuccessMessageComponent></SuccessMessageComponent>
            <ErrorMessageComponent></ErrorMessageComponent>
            <div className="loginForm">
                <div>
                    <h1>Login Mofo</h1>
                    <label>Username:</label>
                    <input type="text" name="username" value={username} onChange={handleUsernameChange}></input>
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange}></input>
                </div>
                <div>
                    <button type="button" name="login" onClick={handleSubmit}>Login</button>
                </div>
            </div>
        </div>
    )
}
