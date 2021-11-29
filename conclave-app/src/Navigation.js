import { NavLink } from 'react-router-dom';
import './Navigation.css';

// provides links for the user to switch between pages
export default function Navigation() {
    return (
        <nav>
            <NavLink className={isActive => isActive ? "selected" : "not-selected"} to='/'>Home</NavLink>
            {" "}
            <NavLink className={isActive => isActive ? "selected" : "not-selected"} to='/about'>About</NavLink>
            {" "}
            <NavLink className={isActive => isActive ? "selected" : "not-selected"} to='/form'>Schedule Form</NavLink>
        </nav>
    );
}

