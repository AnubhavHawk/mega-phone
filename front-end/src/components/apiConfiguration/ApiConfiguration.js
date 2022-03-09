import moment from "moment";
import React, { useEffect, useState } from "react";
import { Navigate } from "react-router";
import Sidebar from "../sidebar/Sidebar";
import Field from "./Field";
import timeFormatImg from "./format.png";

function ApiConfiguration(props) {
    const [endpointUrl, setendpointUrl] = useState('');
    const [apiData, setApiData] = useState([]);
    const [apiFields, setApiFields] = useState([]);
    const [timeField, setTimeField] = useState(null);
    const [contactField, setContactField] = useState(null);
    const [timeFormat, setTimeFormat] = useState("YYYY-MM-DD hh:mm:ss");
    const [formData, setFormData] = useState({});
    const [blrmessage, setBlrMessage] = useState("Your bill is going to expire soon, click the link below $$##$$ to pay now.\n-Bharat Bill Pay")
    function getApi() {
        console.log(endpointUrl)
        fetch(endpointUrl)
        .then(res => res.json())
        .then(res => {
            if(Array.isArray(res)) {
                setApiData(res)
            }
            else {
                if(res === null ){
                    alert("Unable to get the data from endpoint");
                }
                else {
                    setApiData([res])
                }
            }
            // if(res === null || res.length <= 0){
            //     alert('Unable to get the data from the endpoint');
            // }
            // else {
            //     setApiData(res)
            // }
        })
    }
    useEffect(() => {
        if(apiData.length > 0)
            setApiFields(Object.keys(apiData[0]));

        // Create the form data state, that will be sent to the server -> DB
        setFormData({
            timeField,
            timeFormat,
            apiFields,
            endpointUrl,
            contactField,
            blrmessage,
            billerId: props.user.username
        })
    }, [apiData, timeField, timeFormat, contactField, blrmessage])


    function deleteField(key) {
        console.log(key);
        if(apiFields.length > 0) {
            setApiFields(apiFields.filter((field, i) => i !== key))
        }
    }
    function validateTimeFormat(timeFormat) {
        alert(moment(apiData[0][apiFields[timeField]], timeFormat))

    }

    function submitConfigurations() {
        console.log(formData);
    }

    if(props.user === null) {
        return <Navigate to="/login" />
    }
    return(
        <div className="row" style={{minHeight:'80vh'}}>
            <div className="col-md-2 p-0">
                <Sidebar />
            </div>
            <div className="col-md-10 bg-light text-secondary p-md-4">
                <h5 className="text-green"><i className="fas fa-link mr-1"></i>Configure APIs</h5>
                <p>Configure your API endpoints, so the application will automatically start sending the ClickPay links to your customer based on <b>your configurations.</b></p>
                <div className="mt-5 text-secondary">
                    <div className="mt-4">
                        <div className="row">
                            <div className="col-md-10 col-9 d-inline">
                                <input className="rounded-pill form-control" placeholder="https://198.10.115.45:8080/api/v1/customer" value={endpointUrl} onChange={e=>setendpointUrl(e.target.value)}/>
                            </div>
                            <button className="col-md-1 col-2 ml-1 rounded-pill bg-green text-white btn my-btn-hover" onClick={getApi}>Connect</button>
                        </div>
                    </div>
                    {
                        apiData.length <= 0 ? '' : (
                            <div className="mt-4 bg-white rounded p-2 shadow-sm mb-3">
                                <div><b className="text-green">Step 1: </b> Specify which field should be used as <b>Due date</b> to send the ClickPay links.<br/>And mark the field which is customer's mobile number.</div>
                                <hr/>
                                <h6>Fields Available are: </h6>
                                {
                                    apiFields.map(
                                        (field, i) => <Field name={field} key={"field-" + i} fieldId={i} deleteField={deleteField} setTimeField={setTimeField} setContactField={setContactField} />
                                    )
                                }
                            </div>
                        )
                    }
                    
                    {
                        timeField !== null ? (
                            <div className="mt-4 bg-white rounded p-2 shadow-sm">
                                <div><b className="text-green">Step 2: </b> Specify the format of time stored in the selected field (<kbd>{apiFields[timeField]}</kbd>)</div>
                                <hr/>
                                <img src={timeFormatImg} height="200px" />
                                <h6>Provide the format of time stored: <b>
                                    {
                                        apiData.length > 0 ? (apiData[0][apiFields[timeField]]) : ''
                                    }
                                    </b>
                                </h6>
                                <input className="form-control d-inline-block" onChange={e => setTimeFormat(e.target.value)} value={timeFormat} />
                                <button className="pl-3 pr-3 text-green rounded-pill mt-1 btn my-btn-hover" style={{border: '1px solid #1bc972'}} onClick={validateTimeFormat}>Validate</button>
                            </div>
                        ) : ''
                    }
                    {
                        (apiData.length > 0 && contactField !== null && timeField !== null) ? (
                            <div className="mt-4 bg-white rounded p-2 shadow-sm">
                                <small>Specified Fields are: </small><span className="badge badge-success bg-green">{apiFields[contactField]} <i className="fa fa-mobile-alt ml-1"></i></span> <span className="badge badge-success bg-green">{apiFields[timeField]} <i className="fa fa-clock ml-1"></i></span>
                                <div className="mt-2"><b className="text-green">Step 3: </b> Define frequency for sending the ClickPay Links i.e., how many hours before due date the links should be sent to the customer on <kbd>{apiFields[contactField]}</kbd></div>
                                <hr/>
                                <div className="d-flex justify-content-between align-items-center">
                                    <i className="fa fa-bell mr-2"></i>
                                    <input type="number" step="10" className="form-control d-inline" />
                                </div>
                            </div>
                        ) : ''
                    }

                    {
                        (apiData.length > 0 && contactField !== null && timeField !== null) ? (
                            <div className="mt-4 bg-white rounded p-2 shadow-sm">
                                <small>Specified Fields are: </small><span className="badge badge-success bg-green">{apiFields[contactField]} <i className="fa fa-mobile-alt ml-1"></i></span> <span className="badge badge-success bg-green">{apiFields[timeField]} <i className="fa fa-clock ml-1"></i></span>
                                <div className="mt-2"><b className="text-green">Step 4: </b> Specify what message the customers should receive. User <b>$$##$$</b> as placeholder for the ClickPay link in the message i.e., $$##$$ will be replaced by the original ClickPay link in SMS</div>
                                <hr/>
                                <div className="d-flex justify-content-between align-items-center">
                                    <i className="fa fa-comment-alt mr-2"></i>
                                    <textarea className="form-control" value={blrmessage} onChange={e => setBlrMessage(e.target.value)}></textarea>
                                </div>
                            </div>
                        ) : ''
                    }

                    {
                        (apiData.length > 0 && contactField !== null && timeField !== null) ? (
                            <div className="mt-5 mb-5">
                                <p>You are all set to start sending the ClickPay links to your customers</p>
                                <button className="pl-4 pr-4 bg-green btn text-white my-btn-hover rounded-pill" onClick={submitConfigurations}>Create Configurations <i className="fa fa-check-circle text-white ml-1"></i></button>
                            </div>
                        ) : ''
                    }
                    
                </div>
            </div>
        </div>
    )
}
export default ApiConfiguration;