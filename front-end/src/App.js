import React, { useState } from "react";
import './App.css';
import Navbar from "./components/navbar/Navbar";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Account from "./components/account/Account";
import Login from "./components/login/Login";
import logo from "./logo.png";
import UrlShortner from "./components/url/UrlShortner";
import ApiConfiguration from "./components/apiConfiguration/ApiConfiguration";
import TokenConfiguration from "./components/token/TokenConfiguration";

function App() {
  const [user_details, setUserDetails] = useState(localStorage.user !== undefined ? JSON.parse(localStorage.getItem("user")) : null);
  function setDetails(userDetails) {
    setUserDetails(userDetails)
    if(!userDetails.username) {
      localStorage.clear();
      window.location.href="/login"
    }
    else {
      localStorage.setItem("user", JSON.stringify(userDetails))
    }
  }
  return (
      <Router>
        <div className="App">
          <Navbar logo={logo} user={user_details} setUserDetails={setDetails} />
          <Routes>
            <Route exact path="/login" element={<Login logo={logo} setUserDetails={setDetails} />} />
            <Route exact path="/account" element={<Account />} />
            <Route exact path="/url-shortner" element={<UrlShortner user={user_details} />} />
            <Route exact path="/token" element={<TokenConfiguration user={user_details} />} />
            <Route exact path="/configure" element={<ApiConfiguration user={user_details} />} />
          </Routes>
        </div>
      </Router>
  )
}

export default App;