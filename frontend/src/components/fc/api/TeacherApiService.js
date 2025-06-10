import {apiClient} from './ApiClient'
import axios from 'axios';

// export const retrieveHelloWorldBean = () => apiClient.get("hello-world-bean")

// export const retrieveHelloWorldPathVariable = (username, token) => apiClient.get(`hello-world/path-variable/${username}`,{
//     headers: {
//         Authorization: token
//     }
// })

export const retrieveTeacherPassword = (teacher_id) =>
    apiClient.post(`/teachers/authenticate`, null, { params: { teacher_id } });

export const retrieveAllSubjectsForTeacherIdApi = (username) => apiClient.get(`/teachers/${username}/subjects`)

export const retrieveAllMaterialsForTeacherIdApi = (department,subid) => apiClient.get(`/teachers/${department}/${subid}/materials`)

export const retrieveAllAssignmentsForTeacherIdApi = (department,subid) => apiClient.get(`/teachers/${department}/${subid}/assignments`)

export const retrieveAllSubmissionsForTeacherIdApi = (asignment_id) => apiClient.get(`/teachers/${asignment_id}/submissions`)

export const retrieveAllStudentsForTeacherIdApi = (username,subject_id,department) => apiClient.get(`/teachers/${username}/${subject_id}/${department}/students`)

export const retrieveAllChats=(subject_id,department)=>apiClient.get(`/chatroom/${subject_id}/${department}`)
//export const updateStudentMarksForTeacherIdApi = (assignment_id) => apiClient.put(`/teachers/${assignment_id}/marks`)
// export const deleteMaterials=(subid)=>apiClient.delete(`/`)

export const postNewChatApi = (username, subject_id, department, formData) => {
    return apiClient.post(`/chatroom/${username}/${subject_id}/${department}`, formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};

export const uploadNewMaterialApi = (formData) => {
    return apiClient.post(`/upload`, formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};

export const uploadNewAssignmentApi = (formData) => {
    return apiClient.post(`/uploads`, formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};
export const updateMarks = (assignment_id,submission_id,formData) => {
    return apiClient.put(`/teachers/${assignment_id}/${submission_id}/marks`, formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};