import {apiClient} from './ApiClient'
 
// export const retrieveHelloWorldBean = () => apiClient.get("hello-world-bean")
 
// export const retrieveHelloWorldPathVariable = (username, token) => apiClient.get(`hello-world/path-variable/${username}`,{
//     headers: {
//         Authorization: token
//     }
// })
 
export const retrieveStudentPassword = (student_id) =>
    apiClient.post(`/students/authenticate`, null, { params: { student_id } });
 
 
export const retrieveAllSubjectsForStudentIdApi = (username) => apiClient.get(`/students/${username}/subjects`)
 
export const retrieveAllMaterialsForStudentIdApi = (department,subid) => apiClient.get(`/students/${department}/${subid}/materials`)
 
export const retrieveAllAssignmentsForStudentIdApi = (department,subid) => apiClient.get(`/students/${department}/${subid}/assignments`)
 
export const retrieveAllChats=(subject_id,department)=>apiClient.get(`/chatroom/${subject_id}/${department}`)

export const StudentformData = new FormData();

export const postNewChatApi = (username, subject_id, department, formData) => {
    return apiClient.post(`/chatroom/${username}/${subject_id}/${department}`, formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};
 
export const uploadNewSubmissionApi = (formData) => {
    return apiClient.post(`/upload_sub`, formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};
 