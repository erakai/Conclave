import React from 'react';

const path = 'http://localhost:8080/api/schedules';

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
        console.log("Fetching ", path);
        fetch(path)
            .then(response => response.json())
            .then(data => {
                this.setState({response: data});
                console.log(this.state.response);
            })
            .catch((error) => console.error(error));
        alert('test')
    }

    render() {
        return (
            <div>
                <form onSubmit={this.onSubmit}>
                    <label>Raw Schedule String: </label> <br></br>
                    <input type="text" value={this.state.schedule} onChange={this.onChange}/> <br></br>
                    <input type="submit" value="Submit"/>
                </form><br></br>
                <textarea value={this.state.response} readOnly={true}/>
            </div>
        );
    }
}

export default ScheduleForm;