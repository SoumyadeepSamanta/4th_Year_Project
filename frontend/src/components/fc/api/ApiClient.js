import axios from "axios"

// export default function retrieveHelloWorldBean() {

//     return  axios.get("http://localhost:8080/hello-world-bean")
// }

export const apiClient = axios.create(
    {
        baseURL:'https://hp5rn8bb-8080.inc1.devtunnels.ms/'
    }
)