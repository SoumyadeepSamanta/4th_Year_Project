import {apiClient} from './ApiClient'
import axios from 'axios';

// export const retrieveHelloWorldBean = () => apiClient.get("hello-world-bean")

// export const retrieveHelloWorldPathVariable = (username, token) => apiClient.get(`hello-world/path-variable/${username}`,{
//     headers: {
//         Authorization: token
//     }
// })


export const retrieveAllSubjectsForTeacherIdApi = (username) => apiClient.get(`/teachers/${username}/subjects`)

export const retrieveAllMaterialsForTeacherIdApi = (department,subid) => apiClient.get(`/teachers/${department}/${subid}/materials`)

export const uploadNewMaterialApi = (formData) => {
    return apiClient.post(`/upload`, formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};