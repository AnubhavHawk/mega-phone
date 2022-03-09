import React from "react";
import { NavLink } from "react-router-dom";

function Sidebar(props) {
    return(
        <div>
            <div className="bg-green d-flex flex-column">
                <div className="list-group">
                    <NavLink exact activeClassName="active" to="/configure" type="button" className="flex-fill list-group-item list-group-item-action" aria-current="true">
                    <div className="d-flex justify-content-between"><b>API Configuration</b><i className="fas fa-cog"></i></div>
                    </NavLink>
                    <NavLink exact activeClassName="active" to="/token" type="button" className="flex-fill list-group-item list-group-item-action">
                        <div className="d-flex justify-content-between"><b>Authentication Token</b><i className="fas fa-code"></i></div>
                    </NavLink>
                    <NavLink exact activeClassName="active" to="/url-shortner" type="button" className="flex-fill list-group-item list-group-item-action">
                        <div className="d-flex justify-content-between"><b>ClickPay Creation</b><i className="fas fa-link"></i></div>
                    </NavLink>
                </div>
            </div>
        </div>
    )
}
export default Sidebar;