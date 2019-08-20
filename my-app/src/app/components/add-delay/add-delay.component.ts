import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { SupervisorComponent } from '../supervisor/supervisor.component';

@Component({
  selector: 'app-add-delay',
  templateUrl: './add-delay.component.html',
  styleUrls: ['./add-delay.component.scss']
})
export class AddDelayComponent implements OnInit {
 
  addDelayFormGroup: FormGroup;
  constructor(
   public dialogRef: MatDialogRef<SupervisorComponent>,
  @Inject(MAT_DIALOG_DATA) public data: string, private fb:FormBuilder) {}

  ngOnInit(){
    this.addDelayFormGroup=this.fb.group({
      delayControl:['',[Validators.required]]
      
    });
  }
  onNoClick(): void {
  this.dialogRef.close();
}
save()
{
  this.dialogRef.close(this.addDelayFormGroup.value.delayControl);
}

}
