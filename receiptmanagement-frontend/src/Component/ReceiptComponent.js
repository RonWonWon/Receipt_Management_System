import React, { Component } from 'react'
import ReceiptService from '../Service/ReceiptService';
import MenuBar from './MenuBar';
class ReceiptComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            receipt: [],
            mail: "",
            disable: false,
            filter: "ID"
        };
    }

    componentDidMount() {
        ReceiptService.showReceipt().then((Response) => {
            this.setState({ receipt: Response.data })
        });

    }
    
    download(id){
        var link = "http://localhost:8080/receipt/pdf/"+id;
        console.log(link);
        window.open(link);
    }

    keyPress = event => {
        if(event.keyCode===13){
            if(this.state.filter==="ID"){
                ReceiptService.getById(event.target.value).then((Response) => {
                    var obj = Response.data;
                    var arr = [];
                    arr.push(obj);
                    this.setState({receipt: arr});
                });
            }
            if(this.state.filter==="NAME"){
                ReceiptService.getByName(String(event.target.value)).then((Response) => {
                    var obj = Response.data;
                    var arr = [];
                    for(var i in obj){
                        arr.push(obj[i]);
                    }
                    console.log(arr);
                    this.setState({receipt: arr});
                });
            }
            if(this.state.filter==="MOBILE"){
                ReceiptService.getByMob(String(event.target.value)).then((Response) => {
                    var obj = Response.data;
                    var arr = [];
                    for(var i in obj){
                        arr.push(obj[i]);
                    }
                    this.setState({receipt: arr});
                });
            }
        }
    }

    onInputChange = event => {
        this.setState({filter: event.target.value});
    }

    handleInput = event => {
        this.setState({mail: event.target.value});
    };
    
    email(id){
        //this.state.disable = true;
        var link = "http://localhost:8080/receipt/share/mail/"+id+"/"+this.state.mail;
        console.log(link);
        console.log(this.state.mail);
        // fetch(link).then(
        //     (response) =>{
        //         console.log(response);
        //         alert("Mail Sent Successfully");
        //         this.state.disable = false;
        //     } ,(error) =>{
        //         console.log(error);
        //         alert("Operation Failed");
        //         this.state.disable = false;
        //     }
        // ); 
        window.open(link);
    }
    

    render() {
        return (
            <div>
            <MenuBar />
                <div className="form-inline">
                    <input type="search" 
                        className="search-input" 
                        placeholder="Search for..." 
                        onKeyDown={this.keyPress} 
                    />
                    <select className="custom-select" onChange={this.onInputChange}>
                        <option value="ID">ID</option>
                        <option value="NAME">NAME</option>
                        <option value="MOBILE">MOBILE</option>
                    </select>
                </div>
                <div className="container">
                    {this.state.receipt.length === 0 ? "No Record " :
                    this.state.receipt.map((receipt,index) =>(
                        <div class="card" style={{ margin: "2rem" }} key={receipt.id}>
                            <div class="card-body">
                            <h5 class="card-title">#{index+1}</h5>
                                <h5 class="card-title">{receipt.name}</h5>
                                <h6 className="card-text">{receipt.amount} Rs</h6>
                                <p class="card-text">{receipt.description}</p>
                                <h6 className="card-text">{receipt.created_at}</h6>
                                <div class="d-grid gap-2">
                                    <input onChange={this.handleInput} placeholder="Enter email" />
                                    {
                                        this.state.disable===false? <button class="btn btn-danger" onClick={()=>{this.email(receipt.id)}}>Mail</button> : null
                                    }
                                    <button class="btn btn-danger" onClick={()=>{this.download(receipt.id)}}>Download</button>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        )
    }
}

export default ReceiptComponent;