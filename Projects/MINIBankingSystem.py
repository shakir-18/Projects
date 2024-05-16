import sqlite3
from tkinter import *
from tkinter import messagebox
connection=sqlite3.connect("Members.db")
cursor=connection.cursor()
cursor.execute("""CREATE TABLE IF NOT EXISTS bank (Account_no INTEGER PRIMARY KEY,
name TEXT NOT NULL,gender TEXT NOT NULL,age INTEGER NOT NULL,mobile INTEGER NOT NULL,Password INTEGER NOT NULL,balance INTEGER)""")
root=Tk()
root.geometry("500x280")
root.title("BANK")
Frame(root,bg="light green",width=500,height=280).pack()


def create_account():

    new=Tk()
    new.geometry("380x350")
    new.title("NEW ACCOUNT")
    Frame(new,bg="orange",width=380,height=350).pack()
    Label(new,text="CREATE NEW ACCOUNT",fg="blue",font="italic 15 bold",bg="yellow").place(x=75,y=25)
    Label(new,text="Username",fg="blue",bg="yellow",font="italic 10 bold").place(x=30,y=80)
    Label(new, text="Gender", fg="blue", bg="yellow", font="italic 10 bold").place(x=30, y=115)
    Label(new, text="Age", fg="blue", bg="yellow", font="italic 10 bold").place(x=30, y=150)
    Label(new, text="Mobile No", fg="blue", bg="yellow", font="italic 10 bold").place(x=30, y=183)
    Label(new, text="Pin", fg="blue", bg="yellow", font="italic 10 bold").place(x=30, y=220)
    username=StringVar()
    mobile=IntVar()
    password=IntVar()
    gender=StringVar()
    age=IntVar()

    def submit_new():
        try:
            b = username_entry.get()
            c = gender_entry.get()
            d = age_entry.get()
            e = mobile_entry.get()
            f = int(password_entry.get())
            a = int(str(e) + str(d))
            g = 0
            cursor.execute("INSERT INTO bank VALUES (?,?,?,?,?,?,?)", (a, b, c, d, e, f, g))
            connection.commit()
        except ValueError:
            messagebox.showinfo("Error","Enter valid details\nAge,Mobile_no and Pin should be numbers(INTEGERS)!!!")
        except:
            messagebox.showinfo("Exists", "User already exists")

        else:
            messagebox.showinfo("Success", "Congratulations! Your account has been successfully created!!!")

    username_entry=Entry(new,textvariable=username,width=30,bd=2)
    username_entry.place(x=120,y=80)
    gender_entry = Entry(new, textvariable=mobile, width=10, bd=2)
    gender_entry.place(x=120, y=115)
    age_entry= Entry(new, textvariable=password, width=10, bd=2)
    age_entry.place(x=120, y=150)
    mobile_entry = Entry(new, textvariable=gender, width=30, bd=2)
    mobile_entry.place(x=120, y=184)
    password_entry = Entry(new, textvariable=age, width=30, bd=2)
    password_entry.place(x=120, y=220)
    Button(new,text="SUBMIT",fg="blue",bg="yellow",command=submit_new).place(x=170,y=280)


def login():
    old=Tk()
    old.geometry("300x170")
    old.title("LOGIN")
    Frame(old,bg="sky blue",width=300,height=170).pack()
    Label(old,text="LOGIN ACCOUNT",fg="red",font="italic 15 bold",bg="white").place(x=80,y=13)
    Label(old,text="ACCOUNT NO",fg="blue",font="italic 10 bold",bg="white").place(x=10,y=60)
    Label(old, text="PIN", fg="blue", font="italic 10 bold", bg="white").place(x=30, y=90)

    def login_old():
        try:
            p = account_no_entry.get()
            q = password_entry.get()
            cursor.execute("SELECT COUNT(*) FROM bank WHERE Account_no = {} AND Password = {}".format(p, q))
            connection.commit()
            result = cursor.fetchone()
            if result[0] == 1:
                try:
                    l = Tk()
                    l.geometry("565x300")
                    l.title("Login_page")
                    Frame(l, width=565, height=300, bg="light grey").pack()
                    Label(l, text="You are logged in!", fg="blue", font="Italic 25 bold").place(x=135, y=20)
                    Label(l, text="Amount:", font="Italic 12 bold", fg="black").place(x=242, y=100)
                    amount_value_deposit_withdraw = IntVar()
                    amount_entry_deposit_withdraw = Entry(l, width=12, textvariable=amount_value_deposit_withdraw)
                    amount_entry_deposit_withdraw.place(x=240, y=150)

                    def deposit():
                        try:
                            cursor.execute("SELECT balance FROM bank WHERE Account_no = {} and Password = {}".format(account_no_entry.get(),password_entry.get()))
                            connection.commit()
                            present_balance=cursor.fetchone()
                            Amount_deposit = amount_entry_deposit_withdraw.get()
                            cursor.execute("UPDATE bank set balance={}+{} WHERE Account_no = {} and Password = {}".format( present_balance[0],abs(int(Amount_deposit)),account_no_entry.get(),password_entry.get()))
                            connection.commit()
                            messagebox.showinfo("Success", "Amount deposited successfully")
                        except:
                            messagebox.showinfo("Error","Invalid amount details")

                    def withdraw():
                        try:
                            cursor.execute("SELECT balance FROM bank WHERE Account_no={} AND password={}".format(account_no_entry.get(),password_entry.get()))
                            connection.commit()
                            results = cursor.fetchone()
                            Amount_value= amount_entry_deposit_withdraw.get()
                            if int(results[0])<int(Amount_value):
                                messagebox.showinfo("Insufficient","Insufficient balance")
                            else:
                                cursor.execute("UPDATE  bank set balance={}+{} WHERE Account_no = {} and Password = {}".format(int(results[0]), -1 * int(Amount_value), account_no_entry.get(),password_entry.get()))
                                connection.commit()
                                messagebox.showinfo("Success", "Amount withdrawn successfully")
                        except:
                            messagebox.showinfo("Error","Invalid amount details")

                    def account_details():

                        cursor.execute("SELECT * FROM bank WHERE Account_no={} and password={}".format(account_no_entry.get(),password_entry.get()))
                        connection.commit()
                        result3=cursor.fetchone()
                        messagebox.showinfo("Details",f"Name : {result3[1]}\nGender : {result3[2]}\nAge : {result3[3]}\n"
                                                      f"Mobile : {result3[4]}\nBalance : {result3[6]}")

                    def change_password():
                        t=Tk()
                        t.geometry("200x150")
                        t.title("Password_change")
                        Frame(t,width=200,height=150,bg="yellow").pack()
                        Label(t, text="CHANGE PIN", fg="Black", bg="white",font="Italic 12 bold").place(x=50, y=7)
                        Label(t,text="Reset pin",fg="blue",bg="white",font="Italic 8 bold").place(x=25,y=45)
                        Label(t, text="Confirm pin", fg="blue",bg="white",font="Italic 8 bold").place(x=25, y=75)
                        New_pin=IntVar()
                        Confirm_pin=IntVar()
                        New_pin_entry=Entry(t,textvariable=New_pin,width=10)
                        New_pin_entry.place(x=105,y=45)
                        Confirm_pin_entry=Entry(t,textvariable=Confirm_pin,width=10)
                        Confirm_pin_entry.place(x=105,y=75)

                        def change():
                            try:
                                if New_pin_entry.get() != Confirm_pin_entry.get():
                                    messagebox.showinfo("Error", "Kindly enter same pin both entries\nTry again")
                                else:
                                    cursor.execute("UPDATE bank set Password={} WHERE Account_no={}".format(
                                        int(Confirm_pin_entry.get()), account_no_entry.get()))
                                    connection.commit()
                            except:
                                messagebox.showinfo("Error","Enter valid Password")
                            else:
                                messagebox.showinfo("Success", "Congratulations\nPassword successfully changed")

                        Button(t,text="Submit",fg="red",bg="sky blue",font="Italic 10 bold",command=change).place(x=75,y=110)
                        t.mainloop()
                    Button(l, text="WITHDRAW", font="Italic 12 bold", fg="red",command=withdraw).place(x=375, y=200)
                    Button(l, text="DEPOSIT", font="Italic 12 bold", fg="red", command=deposit).place(x=105, y=200)
                    Button(l, text="Account Details", font="Italic 12 bold", fg="red", command=account_details).place(x=220, y=200)
                    Button(l, text="Change password", font="Italic 8 bold", fg="red", command=change_password).place(x=230, y=250)
                    l.mainloop()

                except:
                    messagebox.showinfo("Error", "Something went wrong")
            else:
                messagebox.showinfo("Error", "Invalid account_no or password!\n"
                                             "please enter valid details!!!")

        except:
            print("HIIIIIII")
            messagebox.showinfo("Error","Oops Invalid details \nTry again")
    account_no=IntVar()
    password=IntVar()
    account_no_entry=Entry(old,textvariable=account_no,width=25)
    account_no_entry.place(x=110,y=60)
    password_entry=Entry(old, textvariable=password, width=25)
    password_entry.place(x=110, y=90)
    Button(old,text="Login",fg="blue",bg="yellow",command=login_old).place(x=150,y=120)


def terms_conditions():
    c = Tk()
    c.title("TERMS AND CONDITIONS")
    c.geometry("300x180")
    Frame(c,width=300,height=180,bg="pink").pack()
    Label(c, text="NO TERMS", fg="black",  font="Italic 18 bold").place(x=80,y=20)
    Label(c, text="NO CONDITIONS", fg="black", font="Italic 18 bold").place(x=50,y=60)
    Label(c, text="APRIL FOOL", fg="black",  font="Italic 18 bold").place(x=80,y=100)
    c.mainloop()


Label(root,text="WELCOME TO BANK OF INDIA",font="italic 20",fg="black",bg="white").place(x=55,y=20)
Button(root,text="CREATE NEW ACCOUNT",font="italic 20 bold",fg="blue",bg="yellow",command=create_account).place(x=80,y=80)
Button(root,text="LOGIN",font="italic 20 bold",fg="blue",bg="yellow",command=login).place(x=199,y=155)
Button(root,text="Terms and Conditions",command=terms_conditions).place(x=190,y=230)
root.mainloop()
