import { Outlet } from 'react-router';
import './App.css';
import Navigation from "./Navigation";

function App() {
  // from what I understand, the Outlet element renders the current "page" of the 
  // corresponding URL in the Routers element in index.js.
  
  // Therefore the App element persists when switching between pages
  return (
    <div className="App">
      <header className="App-header">
        <p>Conclave</p>
      </header>
      <Navigation />
      <Outlet />
    </div>
  );
}

export default App;
