import HeaderComponent from "./HeaderComponent";
import { retrieveAllChats, postNewChatApi } from './api/TeacherApiService';
import { useEffect, useState } from "react";
import { useNavigate, useParams } from 'react-router-dom';
import { AuthContext, useAuth } from './security/AuthContext';

export default function SubjectChatroom() {
    const [chat, setChat] = useState([]);
    const [newMessage, setNewMessage] = useState('');
    
    const { subject_id, department } = useParams();
    const authContext = useAuth();
    const navigate = useNavigate();
    const username = authContext.username;

    useEffect(() => {
        refreshChat();
        const interval = setInterval(refreshChat, 10000);
        return () => clearInterval(interval); 
    }, [username, subject_id, department]);

    function refreshChat() {
        retrieveAllChats(subject_id, department)
            .then(response => {
                console.log(response);
                setChat(response.data);
            })
            .catch(error => console.log(error));
    }

    const handleSendMessage = (e) => {
        e.preventDefault();
        if (newMessage.trim()) {
            const formData = new FormData();
            formData.append('chat', newMessage); 

            postNewChatApi(username, subject_id, department, formData)
                .then(response => {
                    console.log('Message sent:', response.data);
                    setNewMessage(''); 
                    refreshChat(); 
                })
                .catch(error => {
                    console.error('Error sending message:', error);
                });
        }
    };

    return (
        <div className="container">
            <HeaderComponent />

            <div className="chat-container" style={styles.chatContainer}>
                {chat.map((chatItem, index) => (
                    <div
                        key={index}
                        style={{
                            ...styles.chatMessage,
                            ...(chatItem.username === username ? styles.selfMessage : styles.otherMessage),
                        }}
                    >
                        <strong>{chatItem.username}:</strong> {chatItem.chat}
                    </div>
                ))}
            </div>

            <form onSubmit={handleSendMessage} style={styles.form}>
                <input
                    type="text"
                    value={newMessage}
                    onChange={(e) => setNewMessage(e.target.value)}
                    placeholder="Type a message..."
                    style={styles.input}
                />
                <button type="submit" style={styles.button}>Send</button>
            </form>
        </div>
    );
}

const styles = {
    chatContainer: {
        border: '1px solid #ccc',
        borderRadius: '8px',
        padding: '10px',
        height: '400px',
        overflowY: 'auto',
        backgroundColor: '#f9f9f9',
        marginBottom: '10px',
    },
    chatMessage: {
        padding: '5px',
        margin: '5px 0',
        borderRadius: '5px',
    },
    selfMessage: {
        backgroundColor: '#e1ffc7',
        alignSelf: 'flex-end', // Align the self messages to the right
        textAlign: 'right',
    },
    otherMessage: {
        backgroundColor: '#e1e1e1',
        alignSelf: 'flex-start', // Align the other messages to the left
        textAlign: 'left',
    },
    form: {
        display: 'flex',
        padding: '10px',
        backgroundColor: '#fff',
    },
    input: {
        flex: 1,
        padding: '10px',
        border: '1px solid #ccc',
        borderRadius: '4px',
        marginRight: '10px',
    },
    button: {
        padding: '10px 15px',
        border: 'none',
        borderRadius: '4px',
        backgroundColor: '#007bff',
        color: '#fff',
        cursor: 'pointer',
    },
};
