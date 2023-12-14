import logo from './logo.svg';
import './App.css';
import { useEffect } from 'react';
import { Route, Routes } from "react-router-dom";
import DepartmentReport from './components/Reports/DepartmentReport';

function App() {

  useEffect(() => {  
    return () => {
      localStorage.clear()
    }
  })

  return(
    <div className="App">
      <DepartmentReport/>
    </div>
  )

  // return (
  //   <div className="App">
  //     <header className="App-header">
  //       <img src={logo} className="App-logo" alt="logo" />
  //       <p>
  //         Edit <code>src/App.js</code> and save to reload.
  //       </p>
  //       <a
  //         className="App-link"
  //         href="https://reactjs.org"
  //         target="_blank"
  //         rel="noopener noreferrer"
  //       >
  //         Learn React
  //       </a>
  //     </header>
  //   </div>
  // );
}

export default App;
