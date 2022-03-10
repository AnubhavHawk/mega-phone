import React, { useState } from "react";
import { Navigate } from "react-router";
import { NavLink } from "react-router-dom";
import Sidebar from "../sidebar/Sidebar";

function UrlShortner(props) {

    const [token, setToken] = useState(null);
    const [formData, setFormData] = useState({originalUrl: null, billerId: props.user.username, validity: 1000}); // Validity in seconds
    const [creationResponse, setCreationResponse] = useState(null);
    function handleSubmit(e){
        e.preventDefault();
        let createUrl = "http://localhost:8080/api/create";

        fetch(createUrl, { 
            method: 'post', 
            headers: new Headers({
                'Authorization': 'Bearer '+token, 
                'Content-Type': 'application/json'
            }), 
            body: JSON.stringify({
            originalUrl: formData.originalUrl,
            billerId: formData.billerId,
            validity: parseInt(formData.validity)
        })
        }).then(res => res.json()).then(res => setCreationResponse(res));

        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        var requestOptions = {
            headers: myHeaders,
            method: 'POST',
            body: JSON.stringify(formData),
            redirect: 'follow'
          };
        console.log(formData)
    }

    if(props.user === null) {
        return <Navigate to="/login" />
    }
    return(
        <div className="row" style={{minHeight:'80vh'}}>
            <div className="col-md-2 p-0">
                <Sidebar />
            </div>
            <div className="col-md-10 bg-light text-secondary p-md-5">
                <h5 className="text-green"><i class="fas fa-link mr-1"></i>ClickPay Generator</h5>
                
                <div className="mt-5 text-secondary">
                    <form className="row">
                        <div className="col-md-8">
                            <label>Enter API Key: <small><NavLink to="/token">Generate now</NavLink></small></label>
                            <input className="form-control mb-3 rounded-pill" type="text" value={token} onChange={e => setToken(e.target.value)} />

                            <label>Enter Long URL:</label>
                            <input className="form-control rounded-pill mb-3" type="text" value={formData.originalUrl} onChange={e => setFormData({originalUrl: e.target.value, billerId: props.user.username, validity: formData.validity})} placeholder="http://airtel.com/pay/username=Abhinav&payment_amount=230&due_date=12313413&region=NORTHLKO&type=prepaid" />

                            
                            <label>Validity in seconds <small>(Optional)</small>:</label>
                            <input className="form-control rounded-pill" type="number" value={formData.validity} onChange={e => setFormData({validity: e.target.value, billerId: props.user.username, originalUrl: formData.originalUrl})} placeholder="Validity in seconds" />
                            
                            <button className="mt-3 pl-4 pr-4 bg-green btn text-white my-btn-hover rounded-pill" onClick={handleSubmit}>Create ClickPay Link <i className="fa fa-check-circle text-white ml-1"></i></button>
                        </div>
                        {
                            creationResponse !== null ?
                                (<div className="col-md-4 bg-dark rounded shadow-sm p-2">
                                    <b className="text-white border-bottm border-white"> <i className="fa fa-database mr-2"></i>Response:</b>
                                    <div style={{wordBreak: "break-word", color:"#a5ffa5"}} className="mt-4">
                                        {
                                            JSON.stringify(creationResponse)
                                        }
                                    </div>
                                </div>) : ''
                        }
                    </form>
                </div>

                <div className="mt-5 mb-b p-md-3 bg-white shadow rounded">
                    <h6>List of APIs Available:</h6>
                    <div className="border p-md-3 mt-4">
                        <code>
                            1. <kbd>POST</kbd> http://localhost:8080/api/create
                            
                            <pre className="mt-2 border p-2">
                            {
                                JSON.stringify(formData)
                                +"\nAuthorization: Bearer "+token
                                
                            }
                            </pre>

                        </code>
                    </div>

                    
                    <div className="border p-md-3 mt-4">
                        <code>
                            2. <kbd>POST</kbd> http://localhost:8080/api/bulk/create
                            
                            <pre className="mt-2 border p-2">
                            {
                                // JSON.stringify(formData)
                                "{\n\t\"billerId\":\"BLR001\",\n\t\"originalUrl\":[\n\t"+
                                    "\thttps://airtel.com/pay/prepaid?usernumber=987654321&amount=23,\n"+
                                    "\t\thttps://airtel.com/pay/prepaid?usernumber=987654321&amount=23,\n"+
                                    "\t\thttps://airtel.com/pay/prepaid?usernumber=987654321&amount=23\n"
                                +"\t]\n}"
                                +"\nAuthorization: Bearer "+token
                                
                            }
                            </pre>

                        </code>
                    </div>

                    <div className="border p-md-3 mt-4">
                        <code>
                            3. <kbd>GET</kbd> http://localhost:8080/api/expire/short-code
                            
                            <pre className="mt-2 border p-2">
                            {
                                "Authorization: Bearer "+token
                                
                            }
                            </pre>

                        </code>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default UrlShortner;