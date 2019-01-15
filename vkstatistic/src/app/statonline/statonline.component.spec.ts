import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatonlineComponent } from './statonline.component';

describe('StatonlineComponent', () => {
  let component: StatonlineComponent;
  let fixture: ComponentFixture<StatonlineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatonlineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatonlineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
