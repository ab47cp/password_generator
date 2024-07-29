import tkinter as tk
from tkinter import messagebox
import random
import string

def generate_random_character():
    characters = string.ascii_letters + string.digits + string.punctuation
    return random.choice(characters)

def generate_random_password(length):
    password = ''.join(generate_random_character() for _ in range(length))
    return password

def determine_password_strength(length):
    if length < 5:
        return "weak"
    elif 5 <= length < 10:
        return "medium"
    else:
        return "strong"

def generate_passwords():
    try:
        num_passwords = int(entry_num_passwords.get())
        password_length = int(entry_password_length.get())
        passwords = [generate_random_password(password_length) for _ in range(num_passwords)]
        strength = determine_password_strength(password_length)
        display_passwords(passwords, strength)
    except ValueError:
        messagebox.showerror("Invalid Input", "Please enter valid numbers for both fields.")

def display_passwords(passwords, strength):
    text_output.delete(1.0, tk.END)  # Clear previous output
    for i, password in enumerate(passwords, 1):
        text_output.insert(tk.END, f"Password {i}: {password}\n")
    text_output.insert(tk.END, f"\nPassword Strength: {strength}")

# Create the main window
root = tk.Tk()
root.title("Random Password Generator")

# Create and place the widgets
label_num_passwords = tk.Label(root, text="Enter the total number of random passwords:")
label_num_passwords.pack()

entry_num_passwords = tk.Entry(root)
entry_num_passwords.pack()

label_password_length = tk.Label(root, text="Enter the length of each password (At least 8 characters): ")
label_password_length.pack()

entry_password_length = tk.Entry(root)
entry_password_length.pack()

generate_button = tk.Button(root, text="Generate Passwords", command=generate_passwords)
generate_button.pack()

text_output = tk.Text(root, height=15, width=50)
text_output.pack()

# Run the application
root.mainloop()