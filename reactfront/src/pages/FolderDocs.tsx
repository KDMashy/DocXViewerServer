import axios from 'axios';
import React, { ChangeEvent, useEffect, useState } from 'react'
import Cookies from 'universal-cookie';
import Documents from '../components/Documents';
import Folders from '../components/Folder';
import '../styles/Main.css';

export interface IFolder {
  id: number,
  folderName: string,
  folderUrl: string,
  description: string,
  color: string
}

export interface IDocument {
  id: number,
  documentName: string,
  documentUrl: string,
  mimeType: string
}

function FolderDocs() {

  const [documentList, setDocumentList] = useState<IDocument[]>([]);
  const [folderList, setFolderList] = useState<IFolder[]>([]);
  const [folderName, setFolderName] = useState<string>('');
  const [folderUrl, setFolderurl] = useState<string>('');
  const [folderDesc, setFolderDesc] = useState<string>('');
  const [folderColor, setFolderColor] = useState<string>('');
  const [docName, setDocName] = useState<string>('');
  const [docUrl, setDocUrl] = useState<string>('');
  const [docMymeType, setDocMymeType] = useState<string>('');
  const [logged, setLogged] = useState(false);

  const cookies = new Cookies();
  var token = cookies.get('token');
  var isLoggedIn = cookies.get('loggedin');

  const handleChange = (evt: ChangeEvent<HTMLInputElement>): void => {
    if (evt.target.name === "folderName"){
      setFolderName(evt.target.value);
    } else if (evt.target.name === "folderUrl"){
      setFolderurl(evt.target.value);
    } else if (evt.target.name === "folderDesc"){
      setFolderDesc(evt.target.value);
    } else if (evt.target.name === "folderColor"){
      setFolderColor(evt.target.value)
    } else if (evt.target.name === "documentName"){
      setDocName(evt.target.value);
    } else if (evt.target.name === "documentUrl"){
      setDocUrl(evt.target.value);
    } else if (evt.target.name === "documentMimeType"){
      setDocMymeType(evt.target.value)
    }
  };

  var getFolderList: IFolder[] = [];
  var getDocumentList: IDocument[] = [];
  const getAll = async (type: string) => {
    const resp = await axios.get(`http://localhost:8080/${type}`, {
      headers: {"Authorization": `Bearer ${token}`}
    })
    var opened: string = JSON.stringify(resp.data).replace('[','').replace(']','');
    if (opened.length > 0) {
      if(type === "folder"){
        var listed: string[] = opened.split('},{');
        listed.forEach(element => {
          if (element.startsWith('{') && element.endsWith('}')){
            getFolderList.push(JSON.parse(`${element}`));
          } else {
            if(element.startsWith('{')){
              getFolderList.push(JSON.parse(`${element}}`));
            } else {
              getFolderList.push(JSON.parse(`{${element}`));
            }
          }
        });
        var listedLength = listed.length;
        if (listedLength != getFolderList.length){
          for(var i = 0; i < (getFolderList.length/2); i++){
            getFolderList.pop();
          }
        }
        setFolderList(getFolderList);
      } else if (type === "document") {
        var listed: string[] = opened.split('},{');
        listed.forEach(element => {
          if (element.startsWith('{') && element.endsWith('}')){
            getDocumentList.push(JSON.parse(`${element}`));
          } else {
            if(element.startsWith('{')){
              getDocumentList.push(JSON.parse(`${element}}`));
            } else {
              getDocumentList.push(JSON.parse(`{${element}`));
            }
          }
        });
        var listedLength = listed.length;
        if (listedLength != getDocumentList.length){
          for(var i = 0; i < (getDocumentList.length/2); i++){
            getDocumentList.pop();
          }
        }
        setDocumentList(getDocumentList);
      }
    }
  }

  useEffect(() => {
    if (isLoggedIn === 'true'){
      setLogged(true);
    } else {
      setLogged(false);
    }
    getAll('folder');
    getAll('document');
  }, [1]);

  const addFolder = async () => {
    await axios.post('http://localhost:8080/folder', {
      folderName: folderName,
      folderUrl: folderUrl,
      description: folderDesc,
      color: folderColor
    }, {
      headers: {"Authorization": `Bearer ${token}`}
    })
    window.location.replace("http://localhost:3000/folders");
  };

  const addDocument = async () => {
    await axios.post('http://localhost:8080/document', {
      documentName: docName,
      documentUrl: docUrl,
      mimeType: docMymeType
    }, {
      headers: {"Authorization": `Bearer ${token}`}
    })
    window.location.replace("http://localhost:3000/folders");
  };

  return (
    <div className='site'>
      <div className='maincontent'>
        <div className='defaultContainer'>
        <h1>Create Folder</h1>
          <input 
            type='text'
            placeholder='Folder name'
            name='folderName'
            value={folderName}
            onChange={handleChange} />
          <input 
            type='text'
            placeholder='Folder url'
            name='folderUrl'
            value={folderUrl}
            onChange={handleChange}/>
          <input 
            type='text'
            placeholder='Folder description'
            name='folderDesc'
            value={folderDesc}
            onChange={handleChange}/>
          <input 
            type='text'
            placeholder='Folder color'
            name='folderColor'
            value={folderColor}
            onChange={handleChange}/>
          <button className='thingCreate' onClick={addFolder}> Add folder </button>
        </div>
        <div className='defaultContainer'>
        <h1>Create document</h1>
          <input 
            type='text'
            placeholder='Folder name'
            name='documentName'
            value={docName}
            onChange={handleChange} />
          <input 
            type='text'
            placeholder='Folder url'
            name='documentUrl'
            value={docUrl}
            onChange={handleChange}/>
          <input 
            type='text'
            placeholder='Folder description'
            name='documentMimeType'
            value={docMymeType}
            onChange={handleChange}/>
          <button className='thingCreate' onClick={addDocument}> Add document </button>
        </div>
        <div className="folderContainer">
          <h2>Projects</h2>
          {folderList.map((folder: IFolder, key: number) => {
            return <Folders key={key} folder={folder} />;
          })}
        </div>
        <div className="documentContainer">
          <h2>Projects</h2>
          {documentList.map((document: IDocument, key: number) => {
            return <Documents key={key} document={document} />;
          })}
        </div>
      </div>
    </div>
  )
}

export default FolderDocs