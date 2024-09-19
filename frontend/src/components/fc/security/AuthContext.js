import { createContext, useContext, useState } from "react";
import {executeBasicAuthenticationService} from '../api/StudentApiService'
import { apiClient } from "../api/ApiClient";

// 1. Create a context
export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

// 2. Share the created context with other components

export default function AuthProvider({children}) {

    // 3. Put some state in context
    const [isAuthenticated, setAuthenticated] = useState(false)

    //setInterval ( () => setNumber(number+1), 10000)

    const [username, setUsername] = useState(null)

    // const [token, setToken] = useState(null)

    function login(username, password) {
        if(username==='T100' && password==='abc123') {
            setAuthenticated(true)
            setUsername(username)
           return true
        }
        else {
            setAuthenticated(false)
            setUsername(null)
            return false
        }
    }

    // async function login(username, password) {

    //     const baToken = 'Basic ' + window.btoa((username+":"+password))

    //     try {
    //         const response = await executeBasicAuthenticationService(baToken)

    //         if(response.status==200) {
    //             setAuthenticated(true)
    //             setUsername(username)
    //             setToken(baToken)
    //             apiClient.interceptors.request.use(
    //                 (config) => {
    //                     console.log("Intercepting and adding a token")
    //                     config.headers.Authorization = baToken
    //                     return config
    //                 }
    //             )
    //         return true
    //         }
    //         else {
    //             logout()
    //             return false
    //         }
    //     } catch(error) {
    //         logout()
    //         return false
    //     }
    // }

    function logout() {
        setAuthenticated(false)
        setUsername(null)
        // setToken(null)
    }

    const valueToBeShared = {isAuthenticated, login, logout, username}

    return (
        <AuthContext.Provider value={ valueToBeShared }>
            {children}
        </AuthContext.Provider>
    )
}