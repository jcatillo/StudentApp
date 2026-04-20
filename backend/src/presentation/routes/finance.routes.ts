import { Router } from 'express';
import { financeController } from '@/container'; // Import from container here!

export const financeRouter = Router(); // Export a finished router instance

financeRouter.get('/balance/:studentId', financeController.getStudentBalance);
financeRouter.get('/history/:studentId', financeController.getTransactionHistory);
financeRouter.post('/pay', financeController.processPayment);