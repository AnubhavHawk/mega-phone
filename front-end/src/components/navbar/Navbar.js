import React from "react";
import {Link} from "react-router-dom";

class Navbar extends React.Component {
    constructor(props) {
        super(props);
        this.styleImg = {
            maxHeight: '70px',
            maxWidth: '80%'
        }
        // this.text = this.text.bind(this)
    }
    
    render() {
        return (
            <nav className="navbar navbar-expand-lg sticky-top pl-sm-5 pr-sm-5 pt-sm-4 pb-sm-4 pt-md-1 pb-md-1 navbar-height navbar-light shadow-sm bg-white">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="/">
                        <img id="nav-img" src={this.props.logo} style={this.styleImg} className="d-inline-block align-top" alt="" />
                    </Link>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNavDropdown" style={{justifyContent: 'flex-end'}}>
                        <ul className="navbar-nav">
                            {
                                this.props.user !== null ? (
                                <li className="nav-item pl-1 pr-2 dropdown">
                                    <b className="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        {this.props.user.username}
                                    </b>
                                    <div className="dropdown-menu mt-2" aria-labelledby="navbarDropdownMenuLink">
                                        <h5 className="dropdown-item" onClick={e => this.props.setUserDetails({})}>Logout</h5>
                                        <Link className="dropdown-item" to="/account">Account</Link>
                                    </div>
                                </li> )
                                : 
                                <li className="nav-item pl-1 pr-2">
                                    <Link className="nav-link btn bg-green pl-4 pr-4 text-white rounded-pill my-btn-hover" to="/login">Login</Link>
                                </li>
                            }
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }
}
export default Navbar;