import React from 'react';

class ScheduleForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {schedule: '', response: ''};

        this.onSubmit = this.onSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    onChange(event) {
        this.setState({schedule: event.target.value})
    }

    onSubmit(event) {
        alert("Submitted schedule " + this.state.schedule);
    }

    render() {
        return (
            <div>
                <form onSubmit={this.onSubmit}>
                    <label>Raw Schedule String: </label> <br></br>
                    <input type="text" value={this.state.schedule} onChange={this.onChange}/> <br></br>
                    <input type="submit" value="Join"/>
                </form><br></br>
                <textarea value={this.state.response}/>
            </div>
        );
    }
}

export default ScheduleForm;