import React, { Component } from 'react'
import ReceiptService from '../Service/ReceiptService'
import './style.css'
import MenuBar from './MenuBar';

class CreateReceiptComponent extends Component {
    constructor(props){
        super(props);
        this.state = {name: '', mobile: '', amount:'', pan_no:'', email:'', description: '', created_at: ''};
    }
    onInputChange = e => {
        this.setState({
            [e.target.name]:e.target.value
        });
    }
    formHandle = e =>{
        e.preventDefault();
        const receipt = {
            name:this.state.name,
            mobile:this.state.mobile,
            amount:this.state.amount,
            pan_no:this.state.pan_no,
            email:this.state.email,
            description:this.state.description,
            created_at:this.state.created_at
        };
        ReceiptService.create(receipt).then(
            (response) =>{
                console.log(response);
                alert("Receipt Added Successfully");
            } ,(error) =>{
                console.log(error);
                alert("Operation Failed");
            }
        );
        
    }
    render() {
        const {name,mobile,amount,pan_no,email,description,created_at} = this.state;
        return (
            <div>
            <MenuBar />
                <div className="container">
                    <div class="card shadow bg-bg" style={{ margin: "5rem" }}>
                        <div class="card-header card-font">
                            Add a receipt
                        </div>
                        <div class="card-body">
                        <form onSubmit={this.formHandle}>
                        <div class="form-group">
                            <label className="font-ch">NAME</label>
                            <input type="text" class="form-control" name="name"   placeholder="Enter Here" value={name} onChange={this.onInputChange} />
                        </div>
                        <div class="form-group">
                            <label className="font-ch">MOBILE</label>
                            <input type="text" class="form-control" name="mobile"   placeholder="Enter Here" value={mobile} onChange={this.onInputChange}/>
                        </div>
                        <div class="form-group">
                            <label className="font-ch">AMOUNT</label>
                            <input type="text" class="form-control" name="amount"   placeholder="Enter Here" value={amount} onChange={this.onInputChange}/>
                        </div>
                        <div class="form-group">
                            <label className="font-ch">PAN</label>
                            <input type="text" class="form-control" name="pan_no"   placeholder="Enter Here" value={pan_no} onChange={this.onInputChange}/>
                        </div>
                        <div class="form-group">
                            <label className="font-ch">E-MAIL</label>
                            <input type="text" class="form-control" name="email"   placeholder="Enter Here" value={email} onChange={this.onInputChange}/>
                        </div>
                        <div class="form-group">
                            <label className="font-ch">DESCRIPTION</label>
                            <input type="text" class="form-control" name="description"   placeholder="Enter Here" value={description} onChange={this.onInputChange}/>
                        </div>
                        <div class="form-group">
                            <label className="font-ch">DATE</label>
                            <input type="date" class="form-control" name="created_at"  placeholder="Enter Here" value={created_at} onChange={this.onInputChange}/>
                        </div>
                        <div class="d-grid gap-2 mt-2">
                                <button class="btn btn-info" type="submit">Save</button>
                            </div>
                    </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
export default CreateReceiptComponent;