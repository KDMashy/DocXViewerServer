import axios from 'axios';
import React from 'react'
import Cookies from 'universal-cookie';
import { IDocument } from '../pages/FolderDocs'

interface Props {
    document: IDocument,
}

const cookies = new Cookies();
var token = cookies.get('token');
const deleteDocument = async (id: number) => {
    await axios.delete(`http://localhost:8080/document/${id}`, {
        headers: {"Authorization": `Bearer ${token}`}
    })
    window.location.replace("http://localhost:3000/folders");
}

const Documents = ({ document }: Props) => {
    return (
        <div className='thingContainer'>
            <div className='content'>
                <span>{document.documentName}</span>
                <span>{document.documentUrl}</span>
                <span>{document.mimeType}</span>
                <button className='thingDelete' onClick={() => {
                    deleteDocument(document.id);
                }}> Delete </button>
            </div>
        </div>
    );
}

export default Documents