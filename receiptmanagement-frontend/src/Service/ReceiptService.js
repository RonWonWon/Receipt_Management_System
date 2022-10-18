import axios from 'axios'

class ReceiptService {

    login = test =>{
        return axios.get(`http://localhost:8080/login/${test}`);
    }
    
    showReceipt(){
        return axios.get(`http://localhost:8080/receipt/show`);
    }
    create = data =>{
        return axios.post("http://localhost:8080/receipt/add", data);
    }
    getById = id =>{
        return axios.get(`http://localhost:8080/receipt/${id}`);
    }

    getByName = name =>{
        return axios.get(`http://localhost:8080/receipt/name/${name}`);
    }
    
    getByMob = mob =>{
        return axios.get(`http://localhost:8080/receipt/mob/${mob}`);
    }
    //download = id =>{
    //    return axios.get(`http://localhost:8080/receipt/pdf/${id}`);
    //}
    share = (id, mailId) =>{
        return axios.get(`http://localhost:8080/receipt/share/mail//${id}/${mailId}`);
    }
}
export default new ReceiptService;