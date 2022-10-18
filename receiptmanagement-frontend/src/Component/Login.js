import React, { Component } from "react";
import "./style.css";
import ReceiptService from "../Service/ReceiptService";
import {Redirect} from 'react-router-dom';

class Login extends Component {

    constructor(props){
        super(props);
        this.state = {
            id: "",
            password: "",
            finishedStep: false
        };
    }

    setId = event =>{
        this.setState({id: event.target.value});
    }

    setPass = event =>{
        this.setState({password: event.target.value});
    }

    validateForm() {
        ReceiptService.login(this.state.id).then(
            (response) =>{
                console.log(this.state.id);
                console.log(this.state.password);
                if(response.data===this.state.password){
                    console.log("ok");
                    //this.props.history.push("/view");
                    //this.setState({finishedStep: true});
                }
                else alert("Incorrect Id or Password");
            } ,(error) =>{
                alert("Operation Failed");
            }
        );
    }

    render(){

        if (this.state.finishedStep === true) {
            return <Redirect to='/view' />
        }

        return (
            <div>
            <div>
                <center>
                    <br></br>
                    <h3>RECEIPT MANAGEMENT SYSTEM</h3>
                    <br></br>
                </center>
            </div>
            <div className="container">
                    <div class="card shadow bg-bg" style={{ margin: "5rem" }}>
                        <div class="card-header card-font">
                            Login
                        </div>
                        <div class="card-body">
                        <form onSubmit={this.validateForm}>
                        <div class="form-group">
                            <label className="font-ch">ID</label>
                            <input type="text" class="form-control" name="id"   placeholder="Enter Here" onChange={this.setID} />
                        </div>
                        <div class="form-group">
                            <label className="font-ch">Password</label>
                            <input type="password" class="form-control" name="password"   placeholder="Enter Here" onChange={this.setPass}/>
                        </div>
                        <div class="d-grid gap-2 mt-2">
                                <button class="btn btn-info" type="submit">Login</button>
                            </div>
                    </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;