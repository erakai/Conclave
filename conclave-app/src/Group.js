import React from 'react';
import ReactDOM from 'react-dom';
import './Group.css';

class GroupForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {group: ''};

        this.onSubmit = this.onSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    onChange(event) {
        this.setState({group: event.target.value})
    }

    onSubmit(event) {
        alert("Group set to " + this.state.group);
    }

    render() {
        return (
        <form onSubmit={this.onSubmit}>
            <label for="Group_Name">Group Name: </label><br></br>
            <input name="group_name" id="Group_Name" type="text" value={this.state.group} onChange={this.onChange}/><br></br>
            <input type="submit" value="Join"/>
        </form>
        );
    }
}

export default GroupForm;