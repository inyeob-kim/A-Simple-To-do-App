import logo from './logo.svg';
import { Fragment, useEffect, useState } from 'react';
import TodoItem from './components/todoItem'

function App() {

  const [todoItems, setTodoItems] = useState(null);

  useEffect(() => {
    // do something on load
    console.log("Hey, I've loaded up!");

    if (!todoItems) {
      // json() parse the body of the response and return it
      fetch(`http://localhost:8080/api/todoItems`).then((response) => response.json())
      .then((data) => {
        console.log("Todo items list: ", data);
        setTodoItems(data);
      })
    }
     
  }, [todoItems])

  function addNewTodoItem() {
    fetch("http://localhost:8080/api/todoItems", {
      headers: {
        "content-type": "applicatoin/json"
      },
      method: "POST",
    }).then((response) => response.json())
    .then((newTodoItem) => {
      console.log(newTodoItem);
      setTodoItems([...todoItems, newTodoItem]);
    })
  }

  function handleDeleteTodoItem(item) {
    const updatedTodoItems = todoItems.filter(targetTodoItem => targetTodoItem.id !== item.id);
    setTodoItems([...updatedTodoItems])
  }

  // ternary operator
  return (
    <>
      <div>
        <button onClick={addNewTodoItem}>Add New Item</button>
      </div>
      <div className="App">
          {todoItems ? todoItems.map((todoItem) => {
            return <TodoItem key={todoItem.id} data={todoItem} emitDeleteTodoItem={handleDeleteTodoItem}/>
          }) : "loading data..."}
      </div>
    </>

  );
}

export default App;
