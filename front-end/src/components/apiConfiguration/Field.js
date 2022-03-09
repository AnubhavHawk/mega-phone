import React from "react";

function Field(props) {
    return(
        <div className="btn-group d-inline-block m-1" role="group">
            <button className="btn btn-danger-outline btn-outline-danger" onClick={e => props.deleteField(props.fieldId)}><i className="fa fa-trash"></i></button>
            <button type="button" className="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                {props.name}
            </button>
            <div className="dropdown-menu">
                <b className="dropdown-item" onClick={e => props.setTimeField(props.fieldId)}>Time Based field <i className="fa fa-clock ml-3 text-dark"></i></b>
                <b className="dropdown-item"  onClick={e => props.setContactField(props.fieldId)}  >Contact Number <i className="fa fa-mobile-alt ml-3"></i></b>
            </div>
        </div>
    )
}
export default Field;