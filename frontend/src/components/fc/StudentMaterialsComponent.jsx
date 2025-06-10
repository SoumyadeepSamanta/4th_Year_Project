import HeaderComponent from "./HeaderComponent"
import { retrieveAllMaterialsForStudentIdApi } from "./api/StudentApiService"
import { useState } from "react"
import { useEffect } from "react"
import { useParams } from "react-router-dom"

export default function StudentMaterialsComponent() {

    const today = new Date()

    const date = new Date(today.getFullYear(), today.getMonth(), today.getDate())

    // const materials = [
    //     {id:1, name:"Database Management Systems",date},
    //     {id:2, name:"Computer Networks",date},
    //     {id:3, name:"Operating Systems",date}     
    // ]

    const {  department,subject_id } = useParams()
    const [materials, setMaterials] = useState([])

    useEffect(() => {
        refreshMaterials()
    }, [ department,subject_id])
    
    function refreshMaterials() {
        retrieveAllMaterialsForStudentIdApi( department,subject_id)
            .then(response => {
                console.log(response)
                setMaterials(response.data)
            })
            .catch(error => console.log(error))
    }
  
    setInterval(refreshMaterials,1000);
    return(

        <div className="container">
            <HeaderComponent></HeaderComponent>
            <h3>Name: </h3>
            <div></div>
                <table className='table'>
                    <thead>
                        <tr>
                            <td>Material Name</td>
                            <td>Material Date</td>
                            {/* <td>Material Path</td> */}
                        </tr>
                    </thead>
                    <tbody>
                        {
                            materials.map(
                                material=>
                                    <tr>
                                        <td>
                                            <a href={`http://localhost:8090/download?filePath=${encodeURIComponent(material.material_path)}`} target='_blank' rel="noreferrer">
                                                {material.material_name}
                                            </a>
                                        </td>
                                        <td>{(material.upload_time).toString()}</td>
                                    </tr>
                            )
                        }
                        
                    </tbody>
                </table>                    
            </div>
    )
}