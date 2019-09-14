/* 定义组件 */
class Clock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {date: new Date()};
    }

    /*
     * 在组件输出到 DOM 后会执行 componentDidMount() 钩子，我们就可以在这个钩子上设置一个定时器。
     * this.timerID 为定时器的 ID，我们可以在 componentWillUnmount() 钩子中卸载定时器。
     */
    componentDidMount() {
        this.timerID = setInterval(
            () => this.tick(),
            1000
        );
    }
    componentWillUnmount() {
        clearInterval(this.timerID);
    }
    tick() {
        this.setState({
            date: new Date()
        });
    }

    render() {
        let myStyle = {
            fontSize: 100,
            color: '#FF0000'
        };
        return (
            <div style={myStyle}>
                <h1>Hello, world!</h1>
                <FormattedDate date={this.state.date} />
            </div>
        );
    }
}

class FormattedDate extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <h2>现在是 {this.props.date.toLocaleTimeString()}.</h2>
        );
    }
}

function tick() {
    /* 使用组件 */
    ReactDOM.render(
        <Clock />,
        document.getElementById('example')
    );
}

setInterval(tick, 1000);