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

    const { subject_id, department } = useParams()
    const [materials, setMaterials] = useState([])

    useEffect(() => {
        refreshMaterials()
    }, [subject_id, department])
    
    function refreshMaterials() {
        retrieveAllMaterialsForStudentIdApi(subject_id, department)
            .then(response => {
                console.log(response)
                setMaterials(response.data)
            })
            .catch(error => console.log(error))
    }

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
//                                     <a href="/download?filePath=C:/Users/ratul/Desktop/Mindtree/College_ID.pdf">
//     {{ material.material_name }}
//   </a>`${part1} ${part2}`
                                    <tr>
                                        <td>
                                            <a href={`https://hp5rn8bb-8080.inc1.devtunnels.ms/download?filePath=${encodeURIComponent(material.material_path)}`}>
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