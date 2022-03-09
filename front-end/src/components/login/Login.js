import React, {useState, userEffect} from "react";
import  { Navigate } from 'react-router-dom'

function Login(props) {

    const [details, setDetails] = useState({username: '', password: ''});
    
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
                window.location.href='/login'
            }
            else {
                res["username"] = details.username
                props.setUserDetails(res)
                window.location.href='/configure'
            }
        });
    }


    return(
        <div>
            <div className="container">
        <div className="d-flex align-items-center justify-content-center p-5" style={{minHeight:"80vh"}}>
            <div className="shadow border rounded row">
                <div className="col-md-6 p-5">
                    <h6 className="text-secondary">
                        {
                            "Today's Date: " + new Date().toLocaleDateString()
                        }
                    </h6>
                    <div className="mt-5 pt-5">
                        <img src={props.logo} alt="" height="50px"/>
                        <h4 className="font-weight-bold mt-2">Welcome to <b>Mega Phone</b></h4>
                        <p className="text-secondary mb-5">The one stop solution for all the click pay link creation and it's distribution.</p>
                        <form className="mt-5" onSubmit={handleSubmit}>
                            <div className="row text-secondary">
                                <div className="form-group input-group-lg col-md-10">
                                    <label><i className="fa fa-user mr-2"></i>Enter Your Username</label>
                                    <input type="text" className="form-control" name="username" required autoComplete="off" value={details.username} onChange={e => setDetails({username: e.target.value, password: details.password}) } />
                                </div>
                                <div className="form-group input-group-lg col-md-10">
                                    <label><i className="fa fa-lock mr-2"></i>Enter Password</label>
                                    <input type="password" className="form-control" name="password" required autoComplete="off" value={details.password} onChange={e => setDetails({password: e.target.value, username: details.username}) } />
                                </div>
                                <div className="form-group input-group-lg col-md-9">
                                    <input type="submit" name="submit" className="btn text-white pl-4 pr-4 bg-green rounded-pill my-btn-hover" value="Login" required/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div className="col-md-6 bg-green p-5 d-flex justify-content-center align-items-center bg-moon">
                    <div>
                        <h1 className="font-weight-bold text-white mb-5">Just couple of clicks and DONE!</h1>
                        <p>
                            Just you have to the very basic one time configurations, and you can start sharing the clickPay links to your customers
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
        </div>
    )
}
export default Login;