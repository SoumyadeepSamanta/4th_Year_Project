import axios from "axios"

// export default function retrieveHelloWorldBean() {

//     return  axios.get("http://localhost:8080/hello-world-bean")
// }

export const apiClient = axios.create(
    {
        baseURL:'http://localhost:8090/'
    }
)