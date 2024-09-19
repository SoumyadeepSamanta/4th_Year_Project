import HeaderComponent from "./HeaderComponent";
import { retrieveAllMaterialsForTeacherIdApi, uploadNewMaterialApi } from "./api/TeacherApiService";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

export default function TeacherMaterialsComponent() {

    const today = new Date();
    const date = new Date(today.getFullYear(), today.getMonth(), today.getDate());

    const { subject_id, department } = useParams();
    const [materials, setMaterials] = useState([]);
    const [showFileInput, setShowFileInput] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);
    const [materialName, setMaterialName] = useState("");

    useEffect(() => {
        refreshMaterials();
    }, [subject_id, department]);

    function refreshMaterials() {
        retrieveAllMaterialsForTeacherIdApi(subject_id, department)
            .then(response => {
                console.log(response);
                setMaterials(response.data);
            })
            .catch(error => console.log(error));
    }

    // Handle file input change
    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    // Handle file upload to the server
    const handleFileUpload = () => {
        if (!selectedFile || !materialName) {
            alert("Please provide a file and a material name!");
            return;
        }

        const formData = new FormData();
        formData.append("file", selectedFile);
        // formData.append("materialName", materialName);
        formData.append("department", department);
        formData.append("subid", subject_id);
        

        uploadNewMaterialApi(formData)
            .then(response => {
                console.log("File uploaded successfully:", response);
                // Reset input fields
                setSelectedFile(null);
                setMaterialName("");
                setShowFileInput(false);
                // Refresh the materials list
                refreshMaterials();
            })
            .catch(error => {
                console.error("Error uploading file:", error);
            });
    };

    return (
        <div className="container">
            <HeaderComponent></HeaderComponent>
            <h3>Materials for Subject: {subject_id} - Department: {department}</h3>

            <button className="btn btn-success m-3" type="button" onClick={() => setShowFileInput(!showFileInput)}>
                {showFileInput ? "Cancel" : "Add New"}
            </button>

            {showFileInput && (
                <div>
                    <div className="form-group">
                        <label htmlFor="materialName">Material Name:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="materialName"
                            value={materialName}
                            onChange={(e) => setMaterialName(e.target.value)}
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
                        <th>Material Name</th>
                        <th>Material Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {materials.map(material => (
                        <tr key={material.id}>
                            <td>
                                <a href={`https://hp5rn8bb-8080.inc1.devtunnels.ms/download?filePath=${encodeURIComponent(material.material_path)}`}>
                                    {material.material_name}
                                </a>
                            </td>
                            <td>{material.upload_time}</td>
                            <td>
                                <button className="btn btn-danger" type="button">Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}