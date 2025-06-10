import HeaderComponent from "./HeaderComponent";
import { retrieveAllAssignmentsForTeacherIdApi, uploadNewAssignmentApi, retrieveAllSubmissionsForTeacherIdApi } from "./api/TeacherApiService";
import { useState, useEffect } from "react";
import { BrowserRouter, Routes, Route, useNavigate, useParams, Link } from 'react-router-dom'


export default function TeacherAssignmentsComponent() {

    const today = new Date();
    const date = new Date(today.getFullYear(), today.getMonth(), today.getDate());

    const navigate = useNavigate()

    const { subject_id, department } = useParams();
    const [assignments, setAssignments] = useState([]);
    const [submissions, setSubmissions] = useState([]);
    const [showFileInput, setShowFileInput] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);
    const [assignmentsName, setAssignmentsName] = useState("");
    const [marksalloted,setmarksalloted]=useState();
    const [last_date,setlastdate]=useState();

    useEffect(() => {
        refreshAssignments();
    }, [subject_id, department]);

    function refreshAssignments() {
        retrieveAllAssignmentsForTeacherIdApi(subject_id, department)
            .then(response => {
                console.log(response);
                setAssignments(response.data);
            })
            .catch(error => console.log(error));
    }

    // Handle file input change
    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    // Handle file upload to the server
    const handleFileUpload = () => {
        if (!selectedFile || !assignmentsName) {
            alert("Please provide a file and a material name!");
            return;
        }

        const formData = new FormData();
        formData.append("file", selectedFile);
        // formData.append("materialName", materialName);
        formData.append("department", department);
        formData.append("subid", subject_id);
        formData.append("max_marks",marksalloted);
        formData.append("last_date",last_date);
        

        uploadNewAssignmentApi(formData)
            .then(response => {
                console.log("File uploaded successfully:", response);
                // Reset input fields
                setSelectedFile(null);
                setAssignmentsName("");
                // setmarksalloted();
                setShowFileInput(false);
                // Refresh the materials list
                refreshAssignments();
            })
            .catch(error => {
                console.error("Error uploading file:", error);
            });
    };

    function gotoSubmissionsPage(asignment_id) {
    
                    // Fetch materials for the given subject_id and department
                    retrieveAllSubmissionsForTeacherIdApi(asignment_id)
                    .then(response => {
                        console.log(response)
                        // Assuming you want to navigate to a new route with the materials data
                        navigate(`/teacher/${asignment_id}/submissions`, { state: { submissions: response.data } })
                    })
                    .catch(error => console.log(error))
        
        }
    

    return (
        <div className="container">
            <HeaderComponent></HeaderComponent>
            <h3>Assignments for Subject: {department}   Department: {subject_id}</h3>

            <button className="btn btn-success m-3" type="button" onClick={() => setShowFileInput(!showFileInput)}>
                {showFileInput ? "Cancel" : "Add New"}
            </button>

            

            {showFileInput && (
                <div>
                    <div className="form-group">
                        <label htmlFor="assignmentName">Assignment Name:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="assignmentName"
                            value={assignmentsName}
                            onChange={(e) => setAssignmentsName(e.target.value)}
                        />
                    </div>
                    
                    <div className="form-group">
                        <label htmlFor="assignmentMarks">Marks Alloted:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="assignmentMarks"
                            value={marksalloted}
                            onChange={(e) => setmarksalloted(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="assignmentDate">Last Date:</label>
                        <input
                            type="datetime-local"
                            className="form-control"
                            id="assignmentDate"
                            value={last_date}
                            onChange={(e) => setlastdate(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="fileInput">Choose File:</label>
                        <input type="file" id="fileInput" onChange={handleFileChange} />
                    </div>

                    <button className="btn btn-primary" onClick={handleFileUpload}>
                        Upload
                    </button>
                </div>
            )}

            <table className="table mt-4">
                <thead>
                    <tr>
                        <th>Assignment Name</th>
                        <th>Assignment Date</th>
                        <th>Marks Alloted</th>
                        <th>Last Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {assignments.map(assignment => (
                        <tr key={assignment.id}>
                            <td>
                                <a href={`http://localhost:8090/download?filePath=${encodeURIComponent(assignment.assignment_path)}`} target='_blank' rel="noreferrer">
                                    {assignment.assignment_name}
                                </a>
                            </td>
                            <td>{assignment.upload_time}</td>
                            <td>{assignment.max_marks}</td>
                            <td>
                                {assignment.last_time}
                            </td>
                            <td>
                                <button className="btn btn-danger" type="button">Delete</button>
                            </td>
                            <td><button className="btn btn-primary" onClick={()=>gotoSubmissionsPage(assignment.assignment_id)}>View</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}