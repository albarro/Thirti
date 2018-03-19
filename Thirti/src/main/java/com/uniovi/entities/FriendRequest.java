package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FriendRequest {
	
		@Id
		@GeneratedValue
		private Long id;
		private String sendUserName;
		private String sendUserEmail;
		private Boolean accepted = false; 
		
		@ManyToOne
		@JoinColumn(name = "userRecived_id")
		private User userRecived;

		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSendUserEmail() {
			return sendUserEmail;
		}
		public void setSendUserEmail(String sendUserEmail) {
			this.sendUserEmail = sendUserEmail;
		}
		public String getSendUserName() {
			return sendUserName;
		}
		public void setSendUserName(String sendUserName) {
			this.sendUserName = sendUserName;
		}


		public FriendRequest(Long id) {
			super();
			this.id = id;
		}
		
		public FriendRequest(User sendUser, User userRecived) {
			super();
			this.sendUserEmail = sendUser.getEmail();
			this.sendUserName = sendUser.getName();
			this.userRecived = userRecived;
		}
	
		public FriendRequest(){
		}
			


		public User getUserRecived() {
			return userRecived;
		}

		public void setUserRecived(User user ) {
			this.userRecived = user;
		}
		
		public Boolean getResend() {
			return accepted;
		}
		public void setAccepted(Boolean accepted) {
			this.accepted = accepted;
		}

}
