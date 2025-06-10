import { createContext, useContext, useState } from "react";
import {retrieveStudentPassword} from '../api/StudentApiService';
import {retrieveTeacherPassword} from '../api/TeacherApiService'
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
 
    async function login(username, password) {
        try {
            let response = await retrieveStudentPassword(username);
            
            if (password === response.data[0].password) {
                setAuthenticated(true);
                setUsername(username);
                return true;  // ✅ Returns a resolved promise with `true`
            } else {
                throw new Error("Invalid student password");
            }
        } catch (error) {
            console.error("Error retrieving student password:", error);
            try {
                let response = await retrieveTeacherPassword(username);
                if (password === response.data[0].password) {
                    setAuthenticated(true);
                    setUsername(username);
                    return true;  // ✅ Returns a resolved promise with `true`
                } else {
                    setAuthenticated(false);
                    setUsername(null);
                    return false;  // ✅ Returns a resolved promise with `false`
                }
            } catch (error) {
                console.error("Error retrieving teacher password:", error);
                setAuthenticated(false);
                setUsername(null);
                return false;  // ✅ Returns `false` in case of an error
            }
        }
    }
    
 
 

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