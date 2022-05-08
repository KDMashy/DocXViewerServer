import { useEffect, useState, useCallback } from 'react'
import axios from 'axios'

function Notfound() {
  return (
    <div className='site'>
      <div className='maincontent'>
        <div className='defaultContainer'>
          <h1>404: page not found</h1>
        </div>
      </div>
    </div>
  )
}

export default Notfound