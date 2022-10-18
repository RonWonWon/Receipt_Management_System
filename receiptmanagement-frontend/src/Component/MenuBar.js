import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import './style.css'

class MenuBar extends Component {
    render() {
        return (
            <div>
                <nav class="navbar navbar-expand-lg navbar-dark bg-bg">
                <div class="container-fluid">
                  <Link class="navbar-brand font-ch" href="#" to="/">Receipt Management System</Link>
                  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                  </button>
                  <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <Link class="nav-link active" aria-current="page" href="#" to="/">View Receipts</Link>
                      </li>
                      <li class="nav-item">
                        <Link class="nav-link active" aria-current="page" href="#" to="/create-receipt">Create Receipt</Link>
                      </li>
                      
                    </ul>      
                  </div>
                </div>
              </nav>
            </div>
        )
    }
}
export default MenuBar