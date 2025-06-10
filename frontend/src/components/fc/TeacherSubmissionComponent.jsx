import HeaderComponent from "./HeaderComponent";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { retrieveAllSubmissionsForTeacherIdApi, updateMarks } from "./api/TeacherApiService";

export default function TeacherSubmissionComponent() {

    const today = new Date();
    const date = new Date(today.getFullYear(), today.getMonth(), today.getDate());

    // const submission_id=111;
    const {submission_id} = useParams();
    const {assignment_id} = useParams();
    const [submissions, setSubmissions] = useState([]);
    const [marks, setMarks] = useState(); // Marks should be a string or number based on your form input type

    useEffect(() => {
        refreshSubmissions();
    }, [submission_id]);

    function refreshSubmissions() {
        retrieveAllSubmissionsForTeacherIdApi(assignment_id)
            .then(response => {
                console.log(response);
                setSubmissions(response.data);
            })
            .catch(error => console.log(error));
    }

    function updateMarksOfStudent(submission_id, marks) {
        const formData = new FormData();
        formData.append("marks", marks);

        updateMarks(assignment_id, submission_id, formData)
            .then(response => { 
                console.log(response);
                setMarks(response.data); // Assuming the response has the updated marks
                refreshSubmissions();
            })
            .catch(error => console.log(error));
    }

    setInterval(refreshSubmissions,1000);
    return (
        <div className="container">
            <HeaderComponent></HeaderComponent> 
            <table className="table mt-4">
                <thead>
                    <tr>
                        <th>Submission Name</th>
                        <th>Submission Date</th>
                        <th>Student Name</th>
                        <th>Marks Obtained</th>
                    </tr>
                </thead>
                <tbody>
                    {submissions.map(submission => (
                        <tr key={submission.id}>
                            <td>
                                <a href={`http://localhost:8090/download?filePath=${encodeURIComponent(submission.submission_path)}`} target='_blank' rel="noreferrer">
                                    {submission.submission_name}
                                </a>
                            </td>
                            <td>{submission.upload_time}</td>
                            <td>{submission.student_id}</td>
                            <td>{submission.marks_obtained}</td>
                            <td>
                                Enter Marks 
                                <input 
                                    type="number" 
                                    value={marks || ''} 
                                    onChange={(e) => setMarks(e.target.value)} 
                                />
                                <button className="btn btn-success"
                                    onClick={() => updateMarksOfStudent(submission.submission_id, marks)}
                                >
                                    Submit
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
