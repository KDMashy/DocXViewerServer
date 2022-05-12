import axios from 'axios';
import React from 'react'
import Cookies from 'universal-cookie';
import { IFolder } from '../pages/FolderDocs';

interface Props {
    folder: IFolder,
}

const cookies = new Cookies();
var token = cookies.get('token');
const deleteFolder = async (id: number) => {
    await axios.delete(`http://localhost:8080/folder/${id}`, {
        headers: {"Authorization": `Bearer ${token}`}
    })
    window.location.replace("http://localhost:3000/folders");
}

const Folders = ({ folder }: Props) => {
    console.log(folder);
    return (
        <div className='thingContainer'>
            <div className='content'>
                <span>{folder.folderName}</span>
                <span>{folder.folderUrl}</span>
                <span>{folder.description}</span>
                <span>{folder.color}</span>
                <button className='thingDelete' onClick={() => {
                    deleteFolder(folder.id);
                }}> Delete </button>
            </div>
        </div>
    );
}

export default Folders