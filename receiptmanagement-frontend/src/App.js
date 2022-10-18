import {BrowserRouter, Route, Switch} from 'react-router-dom';
import MenuBar from './Component/MenuBar';
import ReceiptComponent from './Component/ReceiptComponent';
import CreateReceiptComponent from './Component/CreateReceiptComponent';
import Login from './Component/Login';
import './App.css';
function App() {
  return (
    <BrowserRouter forceRefresh={true}>
    <div className="App">  
       <Switch>
        {/* <Route exact path='/'><Login/></Route> */}
        {/* <Route exact path='/view'><ReceiptComponent/></Route> */}
        <Route exact path='/'><ReceiptComponent/></Route>
        <Route exact path='/create-receipt'><CreateReceiptComponent/></Route>
       </Switch>    
    </div>
    </BrowserRouter>
  );
}

export default App;