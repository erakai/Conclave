import React from 'react';
import './Forms.css';
import ScheduleForm from './Schedule';

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
        <div>
            <form onSubmit={this.onSubmit}>
                <label>Group Name: </label><br></br>
                <input className="group-input" type="text" value={this.state.group} onChange={this.onChange}/><br></br>
                <input className="group-submit" type="submit" value="Join"/>
            </form>
            <ScheduleForm />
        </div>
        );
    }
}

export default GroupForm;