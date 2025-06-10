import HeaderComponent from "./HeaderComponent";
import { retrieveAllAssignmentsForStudentIdApi, uploadNewSubmissionApi } from "./api/StudentApiService";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { AuthContext,useAuth} from './security/AuthContext';
// import { StudentformData } from "./api/StudentApiService";

export default function StudentAssignmentComponent() {
    const today = new Date();
    const date = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    const {  department,subject_id } = useParams();
    const [assignments, setAssignments] = useState([]);
    
    const [selectedFile, setSelectedFile] = useState(null);
    const [uploadingAssignmentId, setUploadingAssignmentId] = useState(null);

    useEffect(() => {
        refreshAssignments();
    }, [subject_id, department]);
    
    function refreshAssignments() {
        retrieveAllAssignmentsForStudentIdApi(subject_id, department)
            .then(response => {
                console.log(response);
                setAssignments(response.data);
            })
            .catch(error => console.log(error));
    }
    setInterval(refreshAssignments,1000);
    function handleFileChange(event) {
        setSelectedFile(event.target.files[0]);
    }
    const authContext=useAuth()
    const student_id=authContext.username
    // const student_id = 'S22001';
    
    function handleSubmit(assignmentId) {
        if (!selectedFile) {
            alert("Please select a file to upload.");
            return;
        }

        const formData = new FormData();
        formData.append("file", selectedFile);
        formData.append("student_id", student_id);
        formData.append("subid", subject_id);
        formData.append("department", department);
        formData.append("assignment_id", assignmentId);

        uploadNewSubmissionApi(formData)
            .then(response => {
                console.log("File uploaded successfully:", response);
                setSelectedFile(null);
                setUploadingAssignmentId(null);
            })
            .catch(error => {
                console.error("Error:", error);
                alert("File upload failed.");
            });
    }

    return (
        <div className="container">
            <HeaderComponent />
            <h3>Name: </h3>
            <table className='table'>
                <thead>
                    <tr>
                        <td>Assignment Name</td>
                        <td>Marks Assigned</td>
                        <td>Given on</td>
                        <td>Last Date</td>
                        <td>Actions</td>
                    </tr>
                </thead>
                <tbody>
                    {assignments.map(assignment => (
                        <tr key={assignment.assignment_id}>
                            <td>
                                <a href={`http://localhost:8090/download?filePath=${encodeURIComponent(assignment.assignment_path)}`} target='_blank' rel="noreferrer">
                                    {assignment.assignment_name}
                                </a>
                            </td>
                            <td>{assignment.max_marks}</td>
                            <td>{assignment.upload_time}</td>
                            <td>{assignment.last_time}</td>
                            <td>
                                <input type="file" onChange={handleFileChange} />
                                <button
                                    className="btn btn-success"
                                    onClick={() => handleSubmit(assignment.assignment_id)}
                                >
                                    Upload
                                </button>
                                {/* <button
                                    className="btn btn-success"
                                    onClick={() => setUploadingAssignmentId(assignment.assignment_id)}
                                >
                                    Submit
                                </button> */}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
