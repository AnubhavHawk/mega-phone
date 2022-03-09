import React, {useState, userEffect} from "react";
import { Navigate } from "react-router";
import Sidebar from "../sidebar/Sidebar";

function TokenConfiguration(props) {

    const [details, setDetails] = useState({username: '', password: ''});
    const [token, setToken] = useState(null);
    let loginUrl = "http://localhost:8080/api/get-token";
    let handleSubmit = (e) => {
        e.preventDefault();
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        var requestOptions = {
            headers: myHeaders,
            method: 'POST',
            body: JSON.stringify(details),
            redirect: 'follow'
          };
        fetch(loginUrl, requestOptions)
        .then(res => res.json())
        .then(res => {
            if(res.token === null) {
                alert("Invalid Password")
                setDetails({username: '', password: ''})
            }
            else {
                setToken({token: res.token, validTill: res.expiry})
            }
        });
    }

    if(props.user === null) {
        return <Navigate to="/login" />
    }
    return(
        <div className="row" style={{minHeight:'80vh'}}>
            <div className="col-md-2 p-0">
                <Sidebar />
            </div>
            <div className="col-md-10 bg-light text-secondary p-md-3">
                <h5 className="text-green"><i className="fas fa-code mr-1"></i>Manage API Keys</h5>
                <p>
                    Generate a new API Key which can be used to create the ClickPay Generation APIs.
                </p>
                <div className="mt-5">
                    {
                        token == null ? (
                            <form className="mt-5" onSubmit={handleSubmit}>
                            <div className="row text-secondary">
                                <div className="form-group input-group-lg col-md-6">
                                    <label><i className="fa fa-user mr-2"></i>Enter Your Username</label>
                                    <input type="text" className="form-control rounded-pill" name="username" required autoComplete="off" value={details.username} onChange={e => setDetails({username: e.target.value, password: details.password}) } />
                                </div>
                                <div className="col-md-6"></div>
                                <div className="form-group input-group-lg col-md-6">
                                    <label><i className="fa fa-lock mr-2"></i>Enter Password</label>
                                    <input type="password" className="form-control rounded-pill" name="password" required autoComplete="off" value={details.password} onChange={e => setDetails({password: e.target.value, username: details.username}) } />
                                </div>
                                <div className="form-group input-group-lg col-md-9">
                                    <input type="submit" name="submit" className="pl-4 pr-4 bg-green btn my-btn-hover text-white rounded-pill my-btn-hover" value="Generate Token" required/>
                                </div>
                            </div>
                        </form>
                        ) : (
                            <div className="pr-5">
                                <b>Token:</b> <input className="form-control mb-3" value={token.token}/>
                                <b>ValidTill: </b> {token.validTill}
                            </div>
                        )
                    }
                </div>
            </div>
        </div>
    )
}
export default TokenConfiguration;